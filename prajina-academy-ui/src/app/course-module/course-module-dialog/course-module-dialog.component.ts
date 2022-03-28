import { Component, EventEmitter, Inject, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CourseModuleService } from '../services/course-module.service';

@Component({
  selector: 'app-course-module-dialog',
  templateUrl: './course-module-dialog.component.html',
  styleUrls: ['./course-module-dialog.component.css']
})
export class CourseModuleDialogComponent implements OnInit {

  @Output() openDialogEvent = new EventEmitter();
  
  statusList = ["draft", "active", "inactive"];
  moduleForm !: FormGroup;
  actionBtn: string = 'Save';

  constructor(private formBuilder: FormBuilder,
     private dialog: MatDialog,
     private moduleService: CourseModuleService,
     @Inject(MAT_DIALOG_DATA) public editData: any,
     private dialogRef: MatDialogRef<CourseModuleDialogComponent>) { }

  ngOnInit(): void {
    this.moduleForm = this.formBuilder.group({
      id:[null],
      name: ['', Validators.required],
      description:[''],
      version: ['', Validators.required],
      status: ['', Validators.required],
      contact:['', Validators.email],
      //startDate:[''],
     // endDate:[''],
      modules:[]
    })

    if(this.editData) {
      this.actionBtn = 'Update';
      this.moduleForm.controls['id'].setValue(this.editData.id);
      this.moduleForm.controls['name'].setValue(this.editData.name);
      this.moduleForm.controls['description'].setValue(this.editData.description);
      this.moduleForm.controls['version'].setValue(this.editData.version);
      this.moduleForm.controls['status'].setValue(this.editData.status);
      this.moduleForm.controls['contact'].setValue(this.editData.contact);
      //this.moduleForm.controls['startDate'].setValue(this.editData.startDate);
      //this.moduleForm.controls['endDate'].setValue(this.editData.endDate);
    }
  }

  save() {
    if(this.editData) {
      this.update();
    } else {
      this.add();
    }
  }

  add() {
    console.log(this.moduleForm.value);
    if(this.moduleForm.valid) {
      this.moduleService.create(this.moduleForm.value)
        .subscribe({
          next:(res)=>{
            //alert("Module added successfully");
            this.moduleForm.reset();
            this.dialogRef.close('save');
          },
          error:()=>{
            alert("Error adding module!")
          } 
        })
    }
  }

  update() {
    console.log(this.moduleForm.value);
    if(this.moduleForm.valid) {
      this.moduleService.update(this.editData.id, this.moduleForm.value)
        .subscribe({
          next:(res)=>{
            //alert("Module updated successfully");
            this.moduleForm.reset();
            this.dialogRef.close('update');
          },
          error:()=>{
            alert("Error updating module!")
          } 
        })
    }
  }

  close() {
    this.dialogRef.close('close');
  }
}

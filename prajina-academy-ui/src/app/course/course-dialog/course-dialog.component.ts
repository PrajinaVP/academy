import { Component, EventEmitter, Inject, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CourseModuleService } from 'src/app/course-module/services/course-module.service';
import { CourseModule } from '../course.module';
import { CourseService } from '../services/course.service';

@Component({
  selector: 'app-dialog',
  templateUrl: './course-dialog.component.html',
  styleUrls: ['./course-dialog.component.css']
})
export class DialogComponent implements OnInit {

  @Output() openDialogEvent = new EventEmitter();
  
  statusList = ["draft", "active", "inactive"];
  courseForm : FormGroup;
  actionBtn: string = 'Save';
  moduleDropDownList: CourseModule[];

  constructor(private formBuilder: FormBuilder,
     private dialog: MatDialog,
     private courseService: CourseService,
     @Inject(MAT_DIALOG_DATA) public editData: any,
     private dialogRef: MatDialogRef<DialogComponent>,
     private moduleService: CourseModuleService) { }

  ngOnInit(): void {

    if(!this.moduleDropDownList) {
      this.populateModuleDropdown();
    }
    
    this.courseForm = this.formBuilder.group({
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
      this.courseForm.controls['id'].setValue(this.editData.id);
      this.courseForm.controls['name'].setValue(this.editData.name);
      this.courseForm.controls['description'].setValue(this.editData.description);
      this.courseForm.controls['version'].setValue(this.editData.version);
      this.courseForm.controls['status'].setValue(this.editData.status);
      this.courseForm.controls['contact'].setValue(this.editData.contact);
      this.courseForm.controls['modules'].setValue(this.editData.modules);
      //this.courseForm.controls['startDate'].setValue(this.editData.startDate);
      //this.courseForm.controls['endDate'].setValue(this.editData.endDate);
    }
  }

  populateModuleDropdown() {
    this.moduleService.getAll()
      .subscribe({
        next:(res)=>{
          console.log("Populating module :: " + res);
          this.moduleDropDownList = res;
        },
        error:(err)=>{
          alert("Error populating modules dropdown: " + err);
        }
    })
  }

  save() {
    if(this.editData) {
      this.update();
    } else {
      this.add();
    }
  }

  add() {
    console.log(this.courseForm.value);
    if(this.courseForm.valid) {
      this.courseService.create(this.courseForm.value)
        .subscribe({
          next:(res)=>{
            //alert("Course added successfully");
            this.courseForm.reset();
            this.dialogRef.close('save');

          },
          error:()=>{
            alert("Error adding course!")
          } 
        })
    }
  }

  update() {
    if(this.courseForm.valid) {
      this.courseService.update(this.editData.id, this.courseForm.value)
        .subscribe({
          next:(res)=>{
            //alert("Course updated successfully");
            this.courseForm.reset();
            this.dialogRef.close('update');
          },
          error:()=>{
            alert("Error updating course!")
          } 
        })
    }
  }

  close() {
   this.dialogRef.close('close');
  }
}

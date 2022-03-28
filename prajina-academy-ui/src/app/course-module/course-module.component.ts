import { Component, NgModule, OnInit, ViewChild } from '@angular/core';

import { CourseModule } from '../course/course.module';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { CourseModuleService } from './services/course-module.service';
import { CourseModuleDialogComponent } from './course-module-dialog/course-module-dialog.component';

@Component({
  selector: 'app-course-module',
  templateUrl: './course-module.component.html',
  styleUrls: ['./course-module.component.css']
})
export class CourseModuleComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'description', 'version', 'status', 'contact', 'action'];
  dataSource!: MatTableDataSource<CourseModule>;
  
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private dialog: MatDialog, private courseModuleService: CourseModuleService) { }

  ngOnInit(): void {
    this.getAllCourseModules();
  }

  getAllCourseModules() {
    this.courseModuleService.getAll()
      .subscribe({
        next:(res)=>{
          console.log(res);
          this.dataSource = new MatTableDataSource(res);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        },
        error:(err)=>{
          alert("Error fetching Modules: " + err);
        }
    })
  }

  edit(row: CourseModule) {
    this.dialog.open(CourseModuleDialogComponent, {
      width: '30%',
      data: row
    }).afterClosed().subscribe(val=>{
      console.log("val edit :: "+val);
      if(val==='update'){
        this.getAllCourseModules();
      }
    });
  }

  delete(id: number) {
    this.courseModuleService.delete(id)
    .subscribe({
      next:(res)=>{
       // alert("CourseModule deleted successfully");
        this.getAllCourseModules();
      },
      error:()=>{
        alert("Error deleting courseModule");
      }  
    })
  }

  openDialog() {
    const dialogRef = this.dialog.open(CourseModuleDialogComponent, {
      width: '30%'
    }).afterClosed().subscribe(val=>{
      if(val==='save') {
        this.getAllCourseModules();
      }
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

}

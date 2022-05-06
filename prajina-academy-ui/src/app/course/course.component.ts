import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { CourseModuleService } from '../course-module/services/course-module.service';

import { Course } from './course';
import { CourseModule } from './course.module';
import { DialogComponent } from './course-dialog/course-dialog.component';
import { CourseService } from './services/course.service';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {
  displayedColumns: string[] = ['id', 'name', 'description', 'version', 'status', 'contact', 'modules', 'action'];
  dataSource!: MatTableDataSource<Course>;
  
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private dialog: MatDialog, private courseService: CourseService, private moduleService: CourseModuleService) { }

  ngOnInit(): void {
    this.getAllCourses();
  }

  getAllCourses() {
    this.courseService.getAll()
      .subscribe({
        next:(res)=>{
          this.dataSource = new MatTableDataSource(res);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        },
        error:(err)=>{
          alert("Error fetching courses: " + err);
        }
    })
  }

  edit(row: Course) {
    this.dialog.open(DialogComponent, {
      width: '30%',
      data: row
    }).afterClosed().subscribe(val=>{
      console.log("val edit :: "+val);
      if(val==='update'){
        this.getAllCourses();
      }
    });
  }

  delete(id: number) {
    this.courseService.delete(id)
    .subscribe({
      next:(res)=>{
       // alert("Course deleted successfully");
        this.getAllCourses();
      },
      error:()=>{
        alert("Error deleting course");
      }  
    })
  }

  openDialog() {
    const dialogRef = this.dialog.open(DialogComponent, {
      width: '30%'
    }).afterClosed().subscribe(val=>{
      if(val==='save') {
        alert("course save");
        this.getAllCourses();
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

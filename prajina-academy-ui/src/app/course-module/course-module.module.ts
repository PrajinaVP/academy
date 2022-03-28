import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from "@angular/material/input";
import { MatSelectModule } from "@angular/material/select";
import { MatDatepickerModule } from "@angular/material/datepicker";
import { MatNativeDateModule } from "@angular/material/core";
import { MatPaginatorModule } from "@angular/material/paginator";
import { MatSortModule } from "@angular/material/sort";
import { ReactiveFormsModule } from '@angular/forms';
import { CourseModuleComponent } from './course-module.component';
import { CourseModuleRoutingModule } from './course-module-routing.module';
import { CourseModuleDialogComponent } from './course-module-dialog/course-module-dialog.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material';



@NgModule({
  declarations: [
    CourseModuleComponent,
    CourseModuleDialogComponent
  ],
  imports: [
    CommonModule,
    CourseModuleRoutingModule,
    MatIconModule,
    MatButtonModule,
    MatInputModule,
    MatToolbarModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    ReactiveFormsModule
  ],
  entryComponents: [
    CourseModuleDialogComponent,
  ]
})

export class CourseModuleModule { }

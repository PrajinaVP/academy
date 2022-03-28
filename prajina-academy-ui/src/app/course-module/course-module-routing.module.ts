import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CourseModuleComponent } from './course-module.component';


const routes: Routes = [
  {path: '', component: CourseModuleComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CourseModuleRoutingModule { }

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';


const routes: Routes = [
  {path: 'home', component: HomeComponent},
  { 
    path: '', 
    loadChildren: () => import('./login/login.module').then(m => m.LoginModule)
  },
  { 
    path: 'course', 
    loadChildren: () => import('./course/course.module').then(m => m.CourseModule)
  },
  {
    path: 'module',
    loadChildren: () => import('./course-module/course-module.module').then(m => m.CourseModuleModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

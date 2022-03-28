import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { CourseModule } from 'src/app/course/course.module';


@Injectable({ providedIn: 'root'})
export class CourseModuleService {

  private readonly apiUrl = 'http://localhost:8081/module';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  constructor(private http: HttpClient) { }

  getAll(): Observable<CourseModule[]> {
    return this.http.get<CourseModule[]>(`${this.apiUrl}/`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    )
  }
    
  // Good practice to add $ to indicate observables - save$ instead of save()
  create(courseModule: CourseModule): Observable<CourseModule> {
    console.log("Before create :: courseModule :: " + JSON.stringify(courseModule))
    return this.http.post<CourseModule>(`${this.apiUrl}`, courseModule)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    )
  }

  find(id: number): Observable<CourseModule> {
    return this.http.get<CourseModule>(`${this.apiUrl}/${id}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    )
  }

  update(id: number, courseModule: CourseModule): Observable<CourseModule> {
    return this.http.put<CourseModule>(`${this.apiUrl}/${id}`, JSON.stringify(courseModule), this.httpOptions) // TODO Is stringify needed?
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    )
  }

  delete(id: number){
    return this.http.delete<CourseModule>(`${this.apiUrl}/${id}`, this.httpOptions)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    )
  }
        

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(() => error);
  }  
}

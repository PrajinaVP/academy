import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { Course } from '../course';



@Injectable({ providedIn: 'root'})
export class CourseService {

  private readonly apiUrl = 'http://localhost:8081/course';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  constructor(private http: HttpClient) { }

  getAll(): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.apiUrl}/`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    )
  }
    
  // Good practice to add $ to indicate observables - save$ instead of save()
  create(course: Course): Observable<Course> {
    console.log("Before create :: course :: " + JSON.stringify(course))
    return this.http.post<Course>(`${this.apiUrl}`, course)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    )
  }

  find(id: number): Observable<Course> {
    return this.http.get<Course>(`${this.apiUrl}/${id}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    )
  }

  update(id: number, course: Course): Observable<Course> {
    return this.http.put<Course>(`${this.apiUrl}/${id}`, JSON.stringify(course), this.httpOptions) // TODO Is stringify needed?
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    )
  }

  delete(id: number){
    return this.http.delete<Course>(`${this.apiUrl}/${id}`, this.httpOptions)
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

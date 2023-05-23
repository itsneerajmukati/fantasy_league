import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthResponse } from '../models/AuthResponse';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {



  constructor(private http: HttpClient) { }

  getUserInContext() {
    const httpOptions = {
      headers: new HttpHeaders({
         'Authorization':'Bearer ' + localStorage.getItem("auth_token")
      })
    };
    return this.http.get<User>("http://localhost:30081/user-service/user/user-in-context",httpOptions)
    .pipe(
      catchError(this.handleError)
    );
  }

  login(username : string, password : string) {
    const httpOptions = {
      headers: new HttpHeaders({
         'Content-Type':'application/json'
      })
    };
    let data = {"username":username,"password":password};
    return this.http.post<AuthResponse>("http://localhost:30081/user-service/user/authenticate", data, httpOptions).toPromise();

  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 403) {
      return throwError(
        'Forbidden');
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, body was: `, error.error);
    }
    // Return an observable with a user-facing error message.
    return throwError(
      'Something bad happened; please try again later.');
  }

  createUser(user: User): Observable<User> {
    const httpOptions = {
      headers: new HttpHeaders({})
    };
    httpOptions.headers = httpOptions.headers.set('Content-Type', 'application/json');

    return this.http.post<User>("http://localhost:30081/user-service/user/create", user, httpOptions)
      .pipe(
        catchError(this.handleError)
      );
  }

}

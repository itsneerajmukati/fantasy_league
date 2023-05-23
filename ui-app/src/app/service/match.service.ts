import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Match } from '../models/Match';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MatchService {


  constructor(private http: HttpClient) { }

  getMatchList() {
    return this.http.get<Match[]>("http://localhost:30081/match-service/match/find-all");
  }

  getMatchById(matchId : number) {
    return this.http.get<Match>("http://localhost:30081/match-service/match/find-by-id?matchId="+matchId);
  }

  addMatch(match: Match): Observable<Match> {
    const httpOptions = {
      headers: new HttpHeaders({})
    };
    httpOptions.headers = httpOptions.headers.set('Content-Type', 'application/json');

    console.log("in match service", httpOptions)
    console.log("in match service", match)
    return this.http.post<Match>("http://localhost:30081/match-service/match/create", match, httpOptions)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {
    alert("errror")
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error);
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

}


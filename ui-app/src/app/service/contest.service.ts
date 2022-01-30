import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Contest } from '../models/Contest';
import { ContestTeam } from '../models/ContestTeam';

@Injectable({
  providedIn: 'root'
})
export class ContestService {

  constructor(private http: HttpClient) { }

  getContestList(matchId: number) {
    return this.http.get<Contest[]>("http://localhost:9000/match-service/contest/find-by-match?matchId=" + matchId);
  }

  getContestTeamList(contestId: number) {
    return this.http.get<ContestTeam[]>("http://localhost:9000/match-service/contest/find-by-contest?contestId="+contestId);
  }

  updateRanks(contestId : number) {
    return this.http.get<string>("http://localhost:9000/match-service/contest/update-rank?contestId="+contestId);
  }



  createContest(contest: Contest): Observable<Contest> {
    const httpOptions = {
      headers: new HttpHeaders({})
    };
    httpOptions.headers = httpOptions.headers.set('Content-Type', 'application/json');

    return this.http.post<Contest>("http://localhost:9000/match-service/contest/create", contest, httpOptions)
      .pipe(
        catchError(this.handleError)
      );
  }

  joinContest(contestTeam: ContestTeam): Observable<Contest> {
    const httpOptions = {
      headers: new HttpHeaders({})
    };
    httpOptions.headers = httpOptions.headers.set('Content-Type', 'application/json');

    return this.http.post<Contest>("http://localhost:9000/match-service/contest/join-contest", contestTeam, httpOptions)
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

import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { PlayerMatchPoint } from '../models/PlayerMatchPoint';
import { Score } from '../models/Score';

@Injectable({
  providedIn: 'root'
})
export class ScoreService {

  constructor(private http: HttpClient) { }

  getPlayerListByTeamId(matchId : number) {
    return this.http.get<PlayerMatchPoint[]>("http://localhost:30081/player-service/score/find-by-match?id="+matchId);
  }

  updateUserTeamPlayerPoints(matchId : number) {
    return this.http.get<string>("http://localhost:30081/player-service/user-team-player/update-players-point?matchId="+matchId);
  }

  updateUserTeamPoints(matchId : number) {
    return this.http.get<string>("http://localhost:30081/team-service/user-team/update-team-point?matchId="+matchId);
  }



  saveScore(score : Score) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':'application/json'
      })
    };
    return this.http.post<string>("http://localhost:30081/player-service/score/calculate-point", score, httpOptions)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {
    console.log("errror", error)
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

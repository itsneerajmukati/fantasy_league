import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Players } from '../models/Players';
import { PlayingEleven } from '../models/PlayingEleven';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  constructor(private http: HttpClient) { }

  getPlayerListByTeamId(teamId : number) {
    return this.http.get<Players[]>("http://localhost:30081/player-service/player/find-by-team-id?teamId="+teamId);
  }

  savePlayer(player : Players) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':'application/json'
      })
    };
    return this.http.post<Players>("http://localhost:30081/player-service/player/create", player, httpOptions)
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

  savePlayingEleven(playingEleven : PlayingEleven[]): Observable<string> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':'application/json'
      })
    };
    return this.http.post<string>("http://localhost:30081/player-service/score/playing-eleven-point", playingEleven, httpOptions)
      .pipe(
        catchError(this.handleError)
      );
  }

}

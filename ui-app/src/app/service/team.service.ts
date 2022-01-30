import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Constant } from '../constant/Constant';
import { Team } from '../models/Team';
import { UserTeam } from '../models/UserTeam';
import { UserTeamPlayer } from '../models/UserTeamPlayer';

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  constructor(private http: HttpClient) { }

  getTeamList() {
    return this.http.get<Team[]>("http://localhost:9000/team-service/sport-team/findAll");
  }

  getTeamById(id : number) {
    return this.http.get<Team>("http://localhost:9000/team-service/sport-team/find-by-id?id="+ id);
  }

  getUserTeamList(matchId: number) {
    return this.http.get<UserTeam[]>("http://localhost:9000/team-service/user-team/by-match-id?matchId="+matchId+"&userId="+Constant.user.id);
  }

  createUserTeam(userTeam : UserTeam) : Promise<UserTeam> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':'application/json'
      })
    };

    return this.http.post<UserTeam>("http://localhost:9000/team-service/user-team/create", userTeam, httpOptions).toPromise();

  }

  createUserTeamPlayers(userTeamPlayers : UserTeamPlayer[]) : Promise<UserTeamPlayer[]> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':'application/json'
      })
    };

    return this.http.post<UserTeamPlayer[]>("http://localhost:9000/player-service/user-team-player/create-bulk", userTeamPlayers, httpOptions).toPromise();
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

  addSportTeam(team: Team): Observable<Team> {
    const httpOptions = {
      headers: new HttpHeaders({})
    };
    httpOptions.headers = httpOptions.headers.set('Content-Type', 'application/json');

    return this.http.post<Team>("http://localhost:9000/team-service/sport-team/create", team, httpOptions)
      .pipe(
        catchError(this.handleError)
      );
  }
  
}

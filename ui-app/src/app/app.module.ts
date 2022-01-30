import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ContestListComponent } from './contest/contest-list.component';
import { LoginComponent } from './login/login.component';
import { MatchDetailComponent } from './match/match-detail.component';
import { MatchListComponent } from './match/match-list.component';
import { ContestTeamListComponent } from './team/contest-team-list.component';
import { PlayerComponent } from './team/player.component';
import { SportTeamDetailComponent } from './team/sport-team-detail.component';
import { SportTeamComponent } from './team/sport-team.component';
import { UserTeamListComponent } from './team/user-team-list.component';
import { MatchPlayerComponent } from './match/match-player.component';
import { ScoreBoardComponent } from './score/score-board.component';


@NgModule({
  declarations: [
    AppComponent,
    MatchListComponent,
    ContestListComponent,
    SportTeamComponent,
    UserTeamListComponent,
    LoginComponent,
    PlayerComponent,
    ContestTeamListComponent,
    SportTeamDetailComponent,
    MatchDetailComponent,
    MatchPlayerComponent,
    ScoreBoardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    FormsModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

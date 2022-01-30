import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
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

const routes: Routes = [
  { path: 'login', component: LoginComponent, pathMatch: 'full' },
  { path: '', component: MatchListComponent, pathMatch: 'full' },
  { path: 'matchDetail', component: MatchDetailComponent, pathMatch: 'full' },
  { path: 'contest/:matchId', component: ContestListComponent, pathMatch: 'full' },
  { path: 'sportTeam', component: SportTeamComponent, pathMatch: 'full' },
  { path: 'userteam', component: UserTeamListComponent, pathMatch: 'full' },
  { path: 'userteam/:contestId', component: ContestTeamListComponent, pathMatch: 'full' },
  { path: 'player/:matchId', component: PlayerComponent, pathMatch: 'full' },
  { path: 'team-detail/:teamId', component: SportTeamDetailComponent, pathMatch: 'full' }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

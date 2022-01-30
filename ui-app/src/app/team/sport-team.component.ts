import { Component, OnInit } from '@angular/core';
import { Team } from '../models/Team';
import { Match } from '../models/Match';
import { MatchService } from '../service/match.service';
import { TeamService } from '../service/team.service';

@Component({
  selector: 'app-sport-team',
  templateUrl: './sport-team.component.html',
  styleUrls: ['./sport-team.component.css']
})
export class SportTeamComponent implements OnInit {

  teamList!: Team[];

  selectedTeams: Team[] = [];

  match: Match = {} as Match;

  venue: string = '';
  teamName: string = '';
  teamCode: string = '';

  constructor(private teamService: TeamService, private matchService : MatchService) { }

  ngOnInit(): void {
    this.teamService.getTeamList().subscribe((data: Team[]) => this.teamList = data);
  }

  selectTeam(team : Team, event:any) {
    console.log(team.name);
    console.log(event.target.checked);
    if(event.target.checked) {
      this.selectedTeams.push(team);
    }
    else {
      this.selectedTeams.splice(this.selectedTeams.indexOf(team), 1);
    }
  }
  
  createMatch() {
    if(this.selectedTeams.length==2) {
      this.match.team1= this.selectedTeams[0];
      this.match.team2= this.selectedTeams[1];
      this.match.venue= this.venue;
      this.matchService.addMatch(this.match).subscribe(match => {this.match=match; alert("Match successfully created.");} );
    }else{
      alert("Please select two teams");
    }
  }

  addTeam() {
    let team = {} as Team;
    team.name = this.teamName;
    team.code = this.teamCode;
    if(this.teamName && this.teamCode) {
      this.teamService.addSportTeam(team).subscribe(data => {this.teamList.push(data); alert("Team successfully created.");} );
    } else {
      alert("Please fill Name and Code");
    }

  }

}

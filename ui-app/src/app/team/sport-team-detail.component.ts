import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Players } from '../models/Players';
import { Team } from '../models/Team';
import { PlayerService } from '../service/player.service';
import { TeamService } from '../service/team.service';

@Component({
  selector: 'app-sport-team-detail',
  templateUrl: './sport-team-detail.component.html',
  styleUrls: ['./sport-team-detail.component.css']
})
export class SportTeamDetailComponent implements OnInit {

  sportTeamId: number = 0;

  team : Team = {} as Team;

  playerList : Players[] = [];

  points : number = 0;

  edit : boolean[] = [];

  add : boolean = false;

  newPlayer : Players = {} as Players; 

  constructor(private route: ActivatedRoute, private playerService : PlayerService, private teamService : TeamService) { }

  ngOnInit(): void {
    const routeParams = this.route.snapshot.paramMap;
    this.sportTeamId = Number(routeParams.get('teamId'));
    this.teamService.getTeamById(this.sportTeamId).subscribe(data => {
      this.team=data;
      this.playerList = data.players;
    });
    //this.playerService.getPlayerListByTeamId(this.sportTeamId).subscribe(data => {this.playerList = data});
  }

  editPoints(i : number) {
    this.edit[i] = !this.edit[i];
  }

  updatePoints(player : Players, i:number) {
    console.log(player);
    this.edit[i] = !this.edit[i];
    player.point = this.points;
    let team = this.team;
    team.players=[];
    player.sportTeams = [team];
    console.log(player);
    this.playerService.savePlayer(player).subscribe(data => {alert("Player updated successfully")});
  }

  addPlayer() {
    console.log(this.newPlayer);
    this.add=!this.add;
    let team = this.team;
    team.players=[];
    this.newPlayer.sportTeams = [team];
    if(this.newPlayer.name && this.newPlayer.point && this.newPlayer.type) {
      this.playerService.savePlayer(this.newPlayer).subscribe(data => {
        alert("Player added successfully");
        this.playerList.push(this.newPlayer);
      });
    } else {
      alert("Please fill details");
      this.add=!this.add;
    }
  }

  changeAdd() {
    console.log("in change");
    this.add=!this.add;
  }

}

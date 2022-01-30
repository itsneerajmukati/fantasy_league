import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Constant } from '../constant/Constant';
import { Match } from '../models/Match';
import { Players } from '../models/Players';
import { UserTeam } from '../models/UserTeam';
import { UserTeamPlayer } from '../models/UserTeamPlayer';
import { MatchService } from '../service/match.service';
import { TeamService } from '../service/team.service';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.css']
})
export class PlayerComponent implements OnInit {

  teamName: string = '';

  selectedPlayers: Players[] = [];

  captainId: number = 0;

  viceCaptainId: number = 0;

  userTeamId: number = 0;

  match: Match = { team1: {}, team2: {} } as Match;

  matchIdFromRoute: number = 0;

  constructor(private teamService: TeamService, private matchService: MatchService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const routeParams = this.route.snapshot.paramMap;
    this.matchIdFromRoute = Number(routeParams.get('matchId'));
    this.matchService.getMatchById(this.matchIdFromRoute).subscribe((data: Match) => this.match = data);
  }

  createTeam() {
    if (this.selectedPlayers.length == 11) {
      let userTeam: UserTeam = {} as UserTeam;
      userTeam.match = { "id": this.matchIdFromRoute } as Match;
      userTeam.name = this.teamName;
      userTeam.user = Constant.user;
      if (userTeam.name) {
        this.teamService.createUserTeam(userTeam).then(res => {
          console.log(res); this.userTeamId = res.id
          console.log(this.userTeamId);
          let userTeamPlayers: UserTeamPlayer[] = [];
          this.selectedPlayers.forEach(
            (player) => {
              let userTeamPlayer: UserTeamPlayer = {} as UserTeamPlayer;
              userTeamPlayer.match = { "id": this.matchIdFromRoute } as Match;
              userTeamPlayer.userTeam = { "id": this.userTeamId } as UserTeam;
              userTeamPlayer.player = player;
              if (player.id == this.captainId) {
                userTeamPlayer.isCaptain = true;
              } else {
                userTeamPlayer.isCaptain = false;
              }
              if (player.id == this.viceCaptainId) {
                userTeamPlayer.isViceCaptain = true;
              } else {
                userTeamPlayer.isViceCaptain = false;
              }
              userTeamPlayers.push(userTeamPlayer);
            }
          )
          this.teamService.createUserTeamPlayers(userTeamPlayers).then(res => { userTeamPlayers = res; alert("User Team successfully created.") }); //subscribe(data => { userTeamPlayers = data; alert("User Team successfully created.");} );
          console.log(userTeamPlayers);

        });
      } else {
        alert("Please fill team name");
      }

    } else {
      alert("Please select 11 players");
    }
  }

  selectPlayer(player: Players, event: any) {
    if (event.target.attributes['selected'].value == "true") {
      event.target.setAttribute("selected", "false");
    } else if (event.target.attributes['selected'].value == "false") {
      event.target.setAttribute("selected", "true");
    }
    console.log(player.name);
    console.log(event.target.attributes['selected'].value);
    console.log(this.selectedPlayers);
    if (event.target.attributes['selected'].value == "true") {
      event.target.parentElement.style.backgroundColor = 'lightgray';
      this.selectedPlayers.push(player);
    }
    else {
      console.log(event.target.attributes['selected'].value);
      event.target.parentElement.style.backgroundColor = '';
      this.selectedPlayers.splice(this.selectedPlayers.indexOf(player), 1);
    }
  }

  selectCaptain(playerId: number, event: any) {
    if (event.target.attributes['selected'].value == "true") {
      event.target.setAttribute("selected", "false");
    } else if (event.target.attributes['selected'].value == "false") {
      event.target.setAttribute("selected", "true");
    }
    if (event.target.attributes['selected'].value == "true") {
      event.target.style.backgroundColor = 'green';
      this.captainId = playerId;
    }
    else {
      event.target.style.backgroundColor = 'yellow';
      this.captainId = 0;
    }
    console.log(this.captainId);
  }

  selectViceCaptain(playerId: number, event: any) {
    if (event.target.attributes['selected'].value == "true") {
      event.target.setAttribute("selected", "false");
    } else if (event.target.attributes['selected'].value == "false") {
      event.target.setAttribute("selected", "true");
    }
    if (event.target.attributes['selected'].value == "true") {
      event.target.style.backgroundColor = 'green';
      this.viceCaptainId = playerId;
    }
    else {
      event.target.style.backgroundColor = 'yellow';
      this.viceCaptainId = 0;
    }
    console.log(this.viceCaptainId);
  }



}

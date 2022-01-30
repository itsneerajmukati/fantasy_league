import { ThrowStmt } from '@angular/compiler';
import { Component, Input, OnInit } from '@angular/core';
import { Match } from '../models/Match';
import { PlayerMatchPoint } from '../models/PlayerMatchPoint';
import { Players } from '../models/Players';
import { Score } from '../models/Score';
import { ScoreService } from '../service/score.service';

@Component({
  selector: 'app-score-board',
  templateUrl: './score-board.component.html',
  styleUrls: ['./score-board.component.css']
})
export class ScoreBoardComponent implements OnInit {

  @Input()
  match : Match = {} as Match;

  playerMatchPointList : PlayerMatchPoint[] = [];

  
  strikeBatsman : Players = {} as Players;
  nonStrikeBatsman : Players = {} as Players;
  bowler : Players = {} as Players;
  wk : Players = {} as Players;
  fielder : Players = {} as Players;
  run : number = 0;
  wicket : string = 'Please Select';
  constructor(private scoreService: ScoreService) { }

  ngOnInit(): void {
    this.scoreService.getPlayerListByTeamId(this.match.id).subscribe( data => {
      this.playerMatchPointList = data;
    });
  }

  selectNBatsman(player: Players, event : any) {
    console.log(event.target.checked);
    console.log(player.name);
    if(event.target.checked) {
      this.nonStrikeBatsman = player;
    } else {
      this.nonStrikeBatsman = {} as Players;
    }
  }

  selectSBatsman(player: Players, event : any) {
    console.log(event.target.checked);
    console.log(player.name);
    if(event.target.checked) {
      this.strikeBatsman = player;
    } else {
      this.strikeBatsman = {} as Players;
    }
  }

  selectBowler(player: Players, event : any) {
    if(event.target.checked) {
      this.bowler=player;
    } else {
      this.bowler= {} as Players;
    }
  }

  selectFielder(player: Players, event : any) {
    if(event.target.checked) {
      this.fielder=player;
    } else {
      this.fielder= {} as Players;
    }
  }

  selectWk(player: Players, event : any) {
    if(event.target.checked) {
      this.wk=player;
    } else {
      this.wk= {} as Players;
    }
  }

  saveScore() {
    let score = {} as Score;
    score.strikeBatsman=this.strikeBatsman.id;
    score.matchId = this.match.id;
    score.run  = this.run;
    if(this.wicket != 'Please Select') {
      score.bowler= this.bowler.id;
      if(this.wicket != 'BOLD' && this.wicket != 'LBW') {
        score.fielder = this.fielder.id;
      }
      if(this.wicket == 'RUNOUT') {
        score.thrower = this.fielder.id;
        score.wicketKeeper = this.wk.id;
      }
      score.wicket = this.wicket;
    }
    if(this.run%2!=0) {
      let temp = this.strikeBatsman;
      this.strikeBatsman = this.nonStrikeBatsman;
      this.nonStrikeBatsman = temp;
    }
    this.scoreService.saveScore(score).subscribe(data => {
      alert("Score updated successully");
    })
  }

  updateUserTeamPlayerPoints() {
    this.scoreService.updateUserTeamPlayerPoints(this.match.id).subscribe(data => {
      alert("Updated successfully");
    })
  }

  updateUserTeamPoints() {
    this.scoreService.updateUserTeamPoints(this.match.id).subscribe(data => {
      alert("Updated successfully");
    })
  }

}

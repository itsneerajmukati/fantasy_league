import { Component, Input, OnInit } from '@angular/core';
import { Match } from '../models/Match';
import { Players } from '../models/Players';
import { PlayingEleven } from '../models/PlayingEleven';
import { PlayerService } from '../service/player.service';

@Component({
  selector: 'app-match-player',
  templateUrl: './match-player.component.html',
  styleUrls: ['./match-player.component.css']
})
export class MatchPlayerComponent implements OnInit {

  @Input()
  match : Match = { team1:{players: [] as Players[]}}  as Match;
  
  selectedPlayers : Players[] = [];
  constructor(private playerSerive : PlayerService) { }

  ngOnInit(): void {
    console.log(this.match);
  }

  selectPlayer(player: Players, event: any) {
    if(event.target.attributes['selected'].value=="true") {
      event.target.setAttribute("selected","false");  
    } else if(event.target.attributes['selected'].value=="false") {
      event.target.setAttribute("selected","true");  
    }
    if(event.target.attributes['selected'].value=="true") {
      event.target.parentElement.style.backgroundColor='lightgray';
      this.selectedPlayers.push(player);
    }
    else {
      console.log(event.target.attributes['selected'].value);
      event.target.parentElement.style.backgroundColor='';
      this.selectedPlayers.splice(this.selectedPlayers.indexOf(player), 1);
    }
  }

  playingEleven() {
    if(this.selectedPlayers.length==22) {
      let playingElevenList = [] as PlayingEleven[];
      this.selectedPlayers.forEach(p => {
        let player = {id:p.id} as Players;
        let match = {id:this.match.id} as Match;
        let playingEleven = {} as PlayingEleven;
        playingEleven.match = match;
        playingEleven.player = player;
        playingElevenList.push(playingEleven);
      });
      this.playerSerive.savePlayingEleven(playingElevenList).subscribe(data=> {
        alert("Score updated for playing eleven");
      });
    }else {
      alert("Please select all 22 players");
    }
  }
}

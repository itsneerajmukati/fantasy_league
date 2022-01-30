import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Match } from '../models/Match';
import { MatchService } from '../service/match.service';

@Component({
  selector: 'app-match-detail',
  templateUrl: './match-detail.component.html',
  styleUrls: ['./match-detail.component.css']
})
export class MatchDetailComponent implements OnInit {

  constructor(private matchService: MatchService, private route: ActivatedRoute) { }

  match : Match = {} as Match;

  isContest : boolean = true;
  isPlayer : boolean = false;
  isScore : boolean = false;
  
  ngOnInit(): void {
    if(history) {
      this.match = history.state;
    }
  }

  showContest() {
    this.isContest = true;
    this.isPlayer = false;
    this.isScore = false;
  }

  showPlayer() {
    this.isContest = false;
    this.isPlayer = true;
    this.isScore = false;
  }

  showScore() {
    this.isContest = false;
    this.isPlayer = false;
    this.isScore = true;
  }

}

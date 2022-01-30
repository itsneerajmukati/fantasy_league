import { Component, OnInit } from '@angular/core';
import { Match } from '../models/Match';
import { MatchService } from '../service/match.service';

@Component({
  selector: 'app-match-list',
  templateUrl: './match-list.component.html',
  styleUrls: ['./match-list.component.css']
})
export class MatchListComponent implements OnInit {
  
  matchList!: Match[];

  constructor(private matchService: MatchService) { }

  ngOnInit(): void {
    this.matchService.getMatchList().subscribe((data: Match[]) => this.matchList = data);
  }

}

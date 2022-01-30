import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ContestTeam } from '../models/ContestTeam';
import { ContestService } from '../service/contest.service';
import { TeamService } from '../service/team.service';

@Component({
  selector: 'app-contest-team-list',
  templateUrl: './contest-team-list.component.html',
  styleUrls: ['./contest-team-list.component.css']
})
export class ContestTeamListComponent implements OnInit {

  contestId: number= 0;

  contestTeamList: ContestTeam[] = [];

  constructor(private contestService : ContestService,  private route: ActivatedRoute) { }

  ngOnInit(): void {
    const routeParams = this.route.snapshot.paramMap;
    this.contestId = Number(routeParams.get('contestId'));

    this.contestService.getContestTeamList(this.contestId).subscribe(data => {this.contestTeamList=data});
  }

}

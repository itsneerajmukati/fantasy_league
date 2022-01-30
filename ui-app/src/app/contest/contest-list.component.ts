import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';
import { Contest } from '../models/Contest';
import { ContestTeam } from '../models/ContestTeam';
import { Match } from '../models/Match';
import { UserTeam } from '../models/UserTeam';
import { ContestService } from '../service/contest.service';

@Component({
  selector: 'app-contest-list',
  templateUrl: './contest-list.component.html',
  styleUrls: ['./contest-list.component.css']
})
export class ContestListComponent implements OnInit {

  contestList!: Contest[];
  @Input()
  matchIdFromRoute : number = 0;
  contentName : string= '';
  fees : number= 0;
  contest: Contest = {} as Contest;
  contestId : number = 0;
  userTeam: UserTeam = {} as UserTeam;
  
  constructor(private contestService: ContestService, private route: ActivatedRoute, config: NgbModalConfig, private modalService: NgbModal, private router: Router) { 
    config.backdrop = 'static';
    config.keyboard = false;
  }

  ngOnInit(): void {
    if(this.matchIdFromRoute) {
      this.contestService.getContestList(this.matchIdFromRoute).subscribe((data: Contest[]) => this.contestList = data);
    }
  }

  createContest() {
    this.contest.match={"id":this.matchIdFromRoute} as Match;
    this.contest.fees= this.fees;
    this.contest.name=this.contentName; 
    if(this.contest.name) {
      this.contestService.createContest(this.contest).subscribe(contest => {this.contestList.push(contest); alert("Contest successfully created.");} );
    }else {
      alert("Please fill contest name");
    }
  }

  open(content : any,contestId:number) {
    this.contestId=contestId;
    this.modalService.open(content);
  }

  selectedTeam(userTeam: UserTeam) {
    this.userTeam=userTeam;
  }

  save() {
    if(this.userTeam.id != null) {
      this.modalService.dismissAll();
      let contestTeam = {} as ContestTeam;
      contestTeam.contest=this.contestList.find(contest => contest.id===this.contestId) as Contest;
      contestTeam.userTeam = this.userTeam;
      this.contestService.joinContest(contestTeam).subscribe(data => {alert("Contest joined successfullt");});
    }
  }

  updateRanks(contest : Contest) {
    this.contestService.updateRanks(contest.id).subscribe(data => {
      alert("Updated successfully");
    })
  }

}




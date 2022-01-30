import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UserTeam } from '../models/UserTeam';
import { TeamService } from '../service/team.service';

@Component({
  selector: 'app-user-team-list',
  templateUrl: './user-team-list.component.html',
  styleUrls: ['./user-team-list.component.css']
})
export class UserTeamListComponent implements OnInit {

  teamList!: UserTeam[];

  @Input() public matchId = 0;

  @Input() public contestId = 0;

  @Output() pickedTeam = new EventEmitter<UserTeam>();

  selectedTeam: UserTeam = {} as UserTeam;

  constructor(private teamService: TeamService, private modalService: NgbModal, private router: Router) { }

  ngOnInit(): void {
    console.log(this.contestId)
    this.teamService.getUserTeamList(this.matchId).subscribe((data: UserTeam[]) => this.teamList = data);
  }

  close() {
    this.modalService.dismissAll();
    this.router.navigate(['player',this.matchId]);
  }

  selectTeam(team: UserTeam, event: any) {
    if(event.target.attributes['selected'].value=="true") {
      event.target.setAttribute("selected","false");  
    } else if(event.target.attributes['selected'].value=="false") {
      event.target.setAttribute("selected","true");  
    }
    if(event.target.attributes['selected'].value=="true") {
      event.target.parentElement.style.backgroundColor='lightgray';
      this.selectedTeam=team;
      this.pickedTeam.emit(this.selectedTeam);
    }
    else {
      event.target.parentElement.style.backgroundColor='';
      this.selectedTeam = {} as UserTeam;
    }
  }

}

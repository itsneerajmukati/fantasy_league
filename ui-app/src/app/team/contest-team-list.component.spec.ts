import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContestTeamListComponent } from './contest-team-list.component';

describe('ContestTeamListComponent', () => {
  let component: ContestTeamListComponent;
  let fixture: ComponentFixture<ContestTeamListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContestTeamListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContestTeamListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

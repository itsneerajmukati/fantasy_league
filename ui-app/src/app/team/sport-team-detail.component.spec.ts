import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SportTeamDetailComponent } from './sport-team-detail.component';

describe('SportTeamDetailComponent', () => {
  let component: SportTeamDetailComponent;
  let fixture: ComponentFixture<SportTeamDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SportTeamDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SportTeamDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

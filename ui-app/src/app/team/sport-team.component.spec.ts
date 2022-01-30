import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SportTeamComponent } from './sport-team.component';

describe('SportTeamComponent', () => {
  let component: SportTeamComponent;
  let fixture: ComponentFixture<SportTeamComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SportTeamComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SportTeamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

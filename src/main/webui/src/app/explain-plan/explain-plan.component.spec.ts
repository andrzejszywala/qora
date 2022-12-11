import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExplainPlanComponent } from './explain-plan.component';

describe('ExplainPlanComponent', () => {
  let component: ExplainPlanComponent;
  let fixture: ComponentFixture<ExplainPlanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExplainPlanComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExplainPlanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

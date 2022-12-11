import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DbmsOutputComponent } from './dbms-output.component';

describe('DbmsOutputComponent', () => {
  let component: DbmsOutputComponent;
  let fixture: ComponentFixture<DbmsOutputComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DbmsOutputComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DbmsOutputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

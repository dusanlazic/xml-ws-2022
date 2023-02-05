import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AutorskaComponent } from './autorska.component';

describe('AutorskaComponent', () => {
  let component: AutorskaComponent;
  let fixture: ComponentFixture<AutorskaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AutorskaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AutorskaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

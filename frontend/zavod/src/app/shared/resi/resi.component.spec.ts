import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResiComponent } from './resi.component';

describe('ResiComponent', () => {
  let component: ResiComponent;
  let fixture: ComponentFixture<ResiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResiComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

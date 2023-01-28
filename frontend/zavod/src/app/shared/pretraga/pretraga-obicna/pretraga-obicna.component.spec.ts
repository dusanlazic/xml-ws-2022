import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PretragaObicnaComponent } from './pretraga-obicna.component';

describe('PretragaObicnaComponent', () => {
  let component: PretragaObicnaComponent;
  let fixture: ComponentFixture<PretragaObicnaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PretragaObicnaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PretragaObicnaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

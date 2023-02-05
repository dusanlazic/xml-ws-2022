import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PretragaRezultatAutorskaComponent } from './pretraga-rezultat-autorska.component';

describe('PretragaRezultatComponent', () => {
  let component: PretragaRezultatAutorskaComponent;
  let fixture: ComponentFixture<PretragaRezultatAutorskaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PretragaRezultatAutorskaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PretragaRezultatAutorskaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

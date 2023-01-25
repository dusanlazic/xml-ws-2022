import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PretragaRezultatComponent } from './pretraga-rezultat.component';

describe('PretragaRezultatComponent', () => {
  let component: PretragaRezultatComponent;
  let fixture: ComponentFixture<PretragaRezultatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PretragaRezultatComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PretragaRezultatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

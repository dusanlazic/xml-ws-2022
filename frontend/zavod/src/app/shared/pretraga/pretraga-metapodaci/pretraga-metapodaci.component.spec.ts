import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PretragaMetapodaciComponent } from './pretraga-metapodaci.component';

describe('PretragaMetapodaciComponent', () => {
  let component: PretragaMetapodaciComponent;
  let fixture: ComponentFixture<PretragaMetapodaciComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PretragaMetapodaciComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PretragaMetapodaciComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

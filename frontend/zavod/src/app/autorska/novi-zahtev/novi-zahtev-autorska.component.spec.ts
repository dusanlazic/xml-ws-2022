import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NoviZahtevAutorskaComponent } from './novi-zahtev-autorska.component';

describe('NoviZahtevComponent', () => {
  let component: NoviZahtevAutorskaComponent;
  let fixture: ComponentFixture<NoviZahtevAutorskaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NoviZahtevAutorskaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NoviZahtevAutorskaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

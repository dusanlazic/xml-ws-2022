import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NoviZahtevComponent } from './novi-zahtev.component';

describe('NoviZahtevComponent', () => {
  let component: NoviZahtevComponent;
  let fixture: ComponentFixture<NoviZahtevComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NoviZahtevComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NoviZahtevComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

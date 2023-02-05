import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NoviZahtevZigComponent } from './novi-zahtev-zig.component';

describe('NoviZahtevComponent', () => {
  let component: NoviZahtevZigComponent;
  let fixture: ComponentFixture<NoviZahtevZigComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NoviZahtevZigComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NoviZahtevZigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

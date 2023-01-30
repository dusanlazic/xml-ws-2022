import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZahtevAutorskaComponent } from './zahtev-autorska.component';

describe('ZahtevAutorskaComponent', () => {
  let component: ZahtevAutorskaComponent;
  let fixture: ComponentFixture<ZahtevAutorskaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZahtevAutorskaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ZahtevAutorskaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

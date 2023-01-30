import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZahtevAutorksaContentComponent } from './zahtev-autorksa-content.component';

describe('ZahtevAutorksaContentComponent', () => {
  let component: ZahtevAutorksaContentComponent;
  let fixture: ComponentFixture<ZahtevAutorksaContentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZahtevAutorksaContentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ZahtevAutorksaContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

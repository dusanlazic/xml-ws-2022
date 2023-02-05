import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZahtevZigContentComponent } from './zahtev-zig-content.component';

describe('ZahetvZigContentComponent', () => {
  let component: ZahtevZigContentComponent;
  let fixture: ComponentFixture<ZahtevZigContentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZahtevZigContentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ZahtevZigContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

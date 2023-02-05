import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PretragaRezultatZigComponent } from './pretraga-rezultat-zig.component';

describe('PretragaRezultatZigComponent', () => {
  let component: PretragaRezultatZigComponent;
  let fixture: ComponentFixture<PretragaRezultatZigComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PretragaRezultatZigComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PretragaRezultatZigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

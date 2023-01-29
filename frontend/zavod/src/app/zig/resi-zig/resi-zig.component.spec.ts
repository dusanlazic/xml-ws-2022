import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResiZigComponent } from './resi-zig.component';

describe('ResiZigComponent', () => {
  let component: ResiZigComponent;
  let fixture: ComponentFixture<ResiZigComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResiZigComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResiZigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

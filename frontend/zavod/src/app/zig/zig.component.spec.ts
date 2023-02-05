import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZigComponent } from './zig.component';

describe('ZigComponent', () => {
  let component: ZigComponent;
  let fixture: ComponentFixture<ZigComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZigComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ZigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

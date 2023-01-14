import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MojiZahteviComponent } from './moji-zahtevi.component';

describe('MojiZahteviComponent', () => {
  let component: MojiZahteviComponent;
  let fixture: ComponentFixture<MojiZahteviComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MojiZahteviComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MojiZahteviComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

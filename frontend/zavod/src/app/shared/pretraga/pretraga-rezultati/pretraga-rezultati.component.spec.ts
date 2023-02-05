import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PretragaRezultatiComponent } from './pretraga-rezultati.component';

describe('PretragaRezultatiComponent', () => {
  let component: PretragaRezultatiComponent;
  let fixture: ComponentFixture<PretragaRezultatiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PretragaRezultatiComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PretragaRezultatiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

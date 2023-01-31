import { Component } from '@angular/core';
import { FieldType, FormlyFieldConfig } from '@ngx-formly/core';
import { AutoriNoviZahtevService } from 'src/services/util/autori-novi-zahtev.service';

@Component({
  selector: 'formly-field-stepper',
  template: `
    <mat-horizontal-stepper [selectedIndex]="step">
      <mat-step *ngFor="let step of field.fieldGroup; let index = index; let last = last">
        <ng-template matStepLabel>{{ step.props!.label }}</ng-template>
        <formly-field [field]="step"></formly-field>
        <div *ngIf="index === 1" style="margin-bottom: 20px;">
          <button mat-raised-button color="warn" *ngIf="number > 1" (click)="decrease()">-1 autor</button>
          <button mat-raised-button color="primary" style="margin-left: 10px;" (click)="increase()">+1 autor</button>
        </div>
        <div>
          <button matStepperPrevious *ngIf="index !== 0" class="btn btn-primary" type="button">Back</button>

          <button matStepperNext *ngIf="!last" class="btn btn-primary" type="button" [disabled]="!isValid(step)">
            Next
          </button>

          <button *ngIf="last" class="btn btn-primary" [disabled]="!form.valid" type="submit">Submit</button>
        </div>
      </mat-step>
    </mat-horizontal-stepper>
  `,
})
export class FormlyFieldStepper extends FieldType {
  
  number: number = 0;

  step: number = 0;

  constructor(private autoriNoviZahtevService: AutoriNoviZahtevService) {
    super();
    autoriNoviZahtevService.number.asObservable().subscribe((data: any) => {
      this.number = data;
      console.log(data);
    });

    autoriNoviZahtevService.step.asObservable().subscribe((data: any) => {
      this.step = data;
    });
  }

  increase() {
    this.number++;
    this.autoriNoviZahtevService.increaseNumber();
  }

  decrease() {
    this.number--;
    this.autoriNoviZahtevService.decreaseNumber();
  }
  
  isValid(field: FormlyFieldConfig): boolean {
    if(field === undefined) return false;
    if (field.key) {
      return field.formControl!.valid;
    }

    return field.fieldGroup ? field.fieldGroup.every((f) => this.isValid(f)) : true;
  }


}

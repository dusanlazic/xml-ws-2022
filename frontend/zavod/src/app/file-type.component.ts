import { Component } from '@angular/core';
import { FieldType, FieldTypeConfig } from '@ngx-formly/core';

@Component({
  selector: 'formly-field-file',
  template: `<div><label>{{props.label}}</label>&nbsp; <input type="file" [formControl]="formControl" [formlyAttributes]="field" /> </div>`,
})
export class FormlyFieldFile extends FieldType<FieldTypeConfig> {}
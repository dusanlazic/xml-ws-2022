import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { FormlyFormOptions, FormlyFieldConfig } from '@ngx-formly/core';

@Component({
  selector: 'app-novi-zahtev',
  templateUrl: './novi-zahtev.component.html',
  styleUrls: ['./novi-zahtev.component.sass']
})
export class NoviZahtevComponent implements OnInit {

	ngOnInit(): void {

	}

	form = new FormGroup({});
	model: any = {};
	options: FormlyFormOptions = {};

	fields: FormlyFieldConfig[] = [
	{
      type: 'stepper',
      fieldGroup: [
        {
          props: { label: 'Personal data' },
          fieldGroup: [
            {
              key: 'firstname',
              type: 'input',
              props: {
                label: 'First name',
                required: true,
              },
            },
            {
              key: 'age',
              type: 'input',
              props: {
                type: 'number',
                label: 'Age',
                required: true,
              },
            },
          ],
        },
        {
          props: { label: 'Destination' },
          fieldGroup: [
            {
              key: 'country',
              type: 'input',
              props: {
                label: 'Country',
                required: true,
              },
            },
          ],
        },
        {
          props: { label: 'Day of the trip' },
          fieldGroup: [
            {
              key: 'day',
              type: 'input',
              props: {
                type: 'date',
                label: 'Day of the trip',
                required: true,
              },
            },
          ],
        },
      ],
    },
  ];

  submit() {
    alert(JSON.stringify(this.model));
  }
}

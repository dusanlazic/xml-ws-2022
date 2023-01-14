import { Component, OnInit } from '@angular/core';
import { FormGroup, RequiredValidator } from '@angular/forms';
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
	model: any = {
    tipPodnosioca: 0,
    podnosilacJeAutor: true,
    autorJeAnoniman: false
  };
	options: FormlyFormOptions = {};

	fields: FormlyFieldConfig[] = [
	{
      type: 'stepper',
      fieldGroup: [
        {
          props: { label: 'Podaci o podnosiocu' },
          fieldGroup: [
            {
              key: 'tipPodnosioca',
              type: 'radio',
              props: {
                label: 'Tip lica',
                required: true,
                options: [
                  {value: 0, label:'Fizičko'},
                  {value: 1, label:'Pravno'}
                ]
              }
            },
            {
              key: 'ime',
              type: 'input',
              props: {
                label: 'Ime',
                required: true,
              },
            },
            {
              key: 'sediste',
              type: 'input',
              props: {
                type: 'text',
                label: 'Sedište',
                required: true,
              },
              expressions: {
                hide: 'model.tipPodnosioca == 0'
              }
            },
            {
              key: 'prezime',
              type: 'input',
              props: {
                label: 'Prezime',
                required: true,
              },
              expressions: {
                hide: 'model.tipPodnosioca == 1'
              }
            },
            {
              key: 'drzavljanstvo',
              type: 'input',
              props: {
                label: 'Državljanstvo',
                required: true,
              },
              expressions: {
                hide: 'model.tipPodnosioca == 1'
              }
            },
            {
              key: 'adresa',
              type: 'input',
              props: {
                type: 'text',
                label: 'Adresa',
                required: true,
              },
              expressions: {
                hide: 'model.tipPodnosioca == 1'
              }
            },
            {
              key: 'telefon',
              type: 'input',
              props: {
                type: 'text',
                label: 'Telefon',
                required: false,
              },
            },
            {
              key: 'email',
              type: 'input',
              props: {
                type: 'text',
                label: 'Email',
                required: false,
              },
            },
            
          ],
        },
        {
          props: { label: 'Podaci o autoru' },
          fieldGroup: [
            {
              key: 'pseudonim',
              type: 'input',
              props: {
                label: 'Pseudonim',
                required: false,
              },
            },
            {
              key: 'podnosilacJeAutor',
              type: 'checkbox',
              props: {
                label: 'Podnosilac je autor',
              },
            },
            {
              key: 'autorJeAnoniman',
              type: 'checkbox',
              props: {
                label: 'Autor je anoniman',
                value: false,
              },
              expressions: {
                hide: 'model.podnosilacJeAutor'
              }
            },
            {
              key: 'ime',
              type: 'input',
              props: {
                label: 'Ime',
                required: true,
              },
              expressions: {
                hide: 'model.podnosilacJeAutor || model.autorJeAnoniman'
              }
            },
            {
              key: 'prezime',
              type: 'input',
              props: {
                label: 'Prezime',
                required: true,
              },
              expressions: {
                hide: 'model.podnosilacJeAutor || model.autorJeAnoniman'
              }
            },
            {
              key: 'drzavljanstvo',
              type: 'input',
              props: {
                label: 'Državljanstvo',
                required: true,
              },
              expressions: {
                hide: 'model.podnosilacJeAutor || model.autorJeAnoniman'
              }
            },
            {
              key: 'datumSmrti',
              type: 'input',
              props: {
                type: 'date',
                label: 'Datum smrti',
                required: false,
              },
              expressions: {
                hide: 'model.podnosilacJeAutor || model.autorJeAnoniman'
              }
            },
            {
              key: 'adresa',
              type: 'input',
              props: {
                type: 'text',
                label: 'Adresa',
                required: true,
              },
              expressions: {
                hide: 'model.podnosilacJeAutor || model.autorJeAnoniman'
              }
            },
          ],
        },

        // ------------------ PODACI O PUNOMOCNIKU ----------------------
        {
          props: { label: 'Podaci o punomoćniku' },
          fieldGroup: [
            {
              key: 'postojiPunomocnik',
              type: 'checkbox',
              props: {
                label: 'Prijava preko punomoćnika',
                required: true
              }
            },
            {
              key: 'ime',
              type: 'input',
              props: {
                label: 'Ime',
                required: true,
              },
              expressions: {
                hide: '!model.postojiPunomocnik'
              }
            },
            {
              key: 'sediste',
              type: 'input',
              props: {
                type: 'text',
                label: 'Sedište',
                required: true,
              },
              expressions: {
                hide: '!model.postojiPunomocnik'
              }
            },
            {
              key: 'prezime',
              type: 'input',
              props: {
                label: 'Prezime',
                required: true,
              },
              expressions: {
                hide: '!model.postojiPunomocnik'
              }
            },
            {
              key: 'drzavljanstvo',
              type: 'input',
              props: {
                label: 'Državljanstvo',
                required: true,
              },
              expressions: {
                hide: '!model.postojiPunomocnik'
              }
            },
            {
              key: 'adresa',
              type: 'input',
              props: {
                type: 'text',
                label: 'Adresa',
                required: true,
              },
              expressions: {
                hide: '!model.postojiPunomocnik'
              }
            },
            {
              key: 'telefon',
              type: 'input',
              props: {
                type: 'text',
                label: 'Telefon',
                required: false,
              },
              expressions: {
                hide: '!model.postojiPunomocnik'
              }
            },
            {
              key: 'email',
              type: 'input',
              props: {
                type: 'text',
                label: 'Email',
                required: false,
              },
              expressions: {
                hide: '!model.postojiPunomocnik'
              }
            },
          ],
        },
        {
          props: { label: 'Podaci o delu' },
          fieldGroup: [
            {
              key: 'naslov',
              type: 'input',
              props: {
                type: 'text',
                label: 'Naslov dela',
                required: true,
              },
            },
            {
              key: 'alternativniNaslov',
              type: 'input',
              props: {
                type: 'text',
                label: 'Alternativni naslov dela',
                required: false,
              },
            },
            {
              key: 'deloJePrerada',
              type: 'checkbox',
              props: {
                label: 'Delo je prerada',
                required: true,
              },
            },
            ////////// AKO JE DELO PRERADA /////////////////
            {
              key: 'originalniNaslov',
              type: 'input',
              props: {
                type: 'text',
                label: 'Originalni naslov dela',
                required: true,
              },
              expressions: {
                hide: 'model.deloJePrerada'
              }
            },
            {
              key: 'originalniAutorIme',
              type: 'input',
              props: {
                type: 'text',
                label: 'Ime originalnog autora',
                required: true,
              },
              expressions: {
                hide: 'model.deloJePrerada'
              }
            },
            {
              key: 'originalniAutorPrezime',
              type: 'input',
              props: {
                type: 'text',
                label: 'Prezime originalnog autora',
                required: true,
              },
              expressions: {
                hide: 'model.deloJePrerada'
              }
            },
            //////////////////////////
            {
              key: 'vrstaDela',
              type: 'input',
              props: {
                type: 'text',
                label: 'Vrsta dela',
                required: true,
              },
            },
            {
              key: 'formaZapisa',
              type: 'input',
              props: {
                type: 'text',
                label: 'Forma zapisa',
                required: true,
              },
            },
            {
              key: 'radniOdnos',
              type: 'checkbox',
              props: {
                label: 'Delo je stvoreno u random odnosu',
                required: true,
              },
            },
            {
              key: 'nacinKoriscenja',
              type: 'input',
              props: {
                type: 'text',
                label: 'Nacin korišćenja dela',
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

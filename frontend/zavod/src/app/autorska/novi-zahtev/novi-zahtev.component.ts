import { Component, OnInit } from '@angular/core';
import { FormGroup, RequiredValidator } from '@angular/forms';
import { FormlyFormOptions, FormlyFieldConfig } from '@ngx-formly/core';
import { HttpRequestService } from 'src/services/util/http-request.service';


@Component({
  selector: 'app-novi-zahtev',
  templateUrl: './novi-zahtev.component.html',
  styleUrls: ['./novi-zahtev.component.sass']
})
export class NoviZahtevComponent implements OnInit {

	ngOnInit(): void {

	}

  constructor(private httpRequestService: HttpRequestService) {

  }

	form = new FormGroup({});
	model: any = {
    tipPodnosioca: 0,
    podnosilacJeAutor: true,
    Autor:{anoniman: undefined}
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
              key: 'Podnosilac.ime',
              type: 'input',
              props: {
                label: 'Ime',
                required: true,
              },
            },
            {
              key: 'Podnosilac.sediste',
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
              key: 'Podnosilac.prezime',
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
              key: 'Podnosilac.drzavljanstvo',
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
              key: 'Podnosilac.adresa',
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
              key: 'Podnosilac.telefon',
              type: 'input',
              props: {
                type: 'text',
                label: 'Telefon',
                required: false,
              },
            },
            {
              key: 'Podnosilac.email',
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
              key: 'podnosilacJeAutor',
              type: 'checkbox',
              props: {
                label: 'Podnosilac je autor',
              },
            },
            {
              key: 'Autor.anoniman',
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
              key: 'Autor.ime',
              type: 'input',
              props: {
                label: 'Ime',
                required: true,
              },
              expressions: {
                hide: 'model.podnosilacJeAutor || model.Autor.anoniman'
              }
            },
            {
              key: 'Autor.prezime',
              type: 'input',
              props: {
                label: 'Prezime',
                required: true,
              },
              expressions: {
                hide: 'model.podnosilacJeAutor || model.Autor.anoniman'
              }
            },
            {
              key: 'Autor.pseudonim',
              type: 'input',
              props: {
                label: 'Pseudonim',
                required: false,
              },
              expressions: {
                hide: 'model.podnosilacJeAutor || model.Autor.anoniman'
              }
            },
            {
              key: 'Autor.drzavljanstvo',
              type: 'input',
              props: {
                label: 'Državljanstvo',
                required: true,
              },
              expressions: {
                hide: 'model.podnosilacJeAutor || model.Autor.anoniman'
              }
            },
            {
              key: 'Autor.godina_smrti',
              type: 'input',
              props: {
                type: 'date',
                label: 'Datum smrti',
                required: false,
              },
              expressions: {
                hide: 'model.podnosilacJeAutor || model.Autor.anoniman'
              }
            },
            {
              key: 'Autor.adresa',
              type: 'input',
              props: {
                type: 'text',
                label: 'Adresa',
                required: true,
              },
              expressions: {
                hide: 'model.podnosilacJeAutor || model.Autor.anoniman'
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
              key: 'Punomocnik.ime',
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
              key: 'Punomocnik.prezime',
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
              key: 'Punomocnik.drzavljanstvo',
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
              key: 'Punomocnik.adresa',
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
              key: 'Punomocnik.telefon',
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
              key: 'Punomocnik.email',
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
              key: 'Delo.naslov',
              type: 'input',
              props: {
                type: 'text',
                label: 'Naslov dela',
                required: true,
              },
            },
            {
              key: 'Delo.alternativni_naslov',
              type: 'input',
              props: {
                type: 'text',
                label: 'Alternativni naslov dela',
                required: false,
              },
            },
            
            {
              key: 'Delo.vrsta_dela',
              type: 'input',
              props: {
                type: 'text',
                label: 'Vrsta dela',
                required: true,
              },
            },
            {
              key: 'Delo.forma_zapisa',
              type: 'input',
              props: {
                type: 'text',
                label: 'Forma zapisa',
                required: true,
              },
            },
            {
              key: 'Delo.radni_odnos',
              type: 'checkbox',
              props: {
                label: 'Delo je stvoreno u random odnosu',
                required: true,
              },
            },
            {
              key: 'Delo.nacin_koriscenja',
              type: 'input',
              props: {
                type: 'text',
                label: 'Nacin korišćenja dela',
                required: true,
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
              key: 'Delo.Prerada.originalni_naslov',
              type: 'input',
              props: {
                type: 'text',
                label: 'Originalni naslov dela',
                required: true,
              },
              expressions: {
                hide: '!model.deloJePrerada'
              }
            },
            {
              key: 'Delo.Prerada.Originalni_Autor.ime',
              type: 'input',
              props: {
                type: 'text',
                label: 'Ime originalnog autora',
                required: true,
              },
              expressions: {
                hide: '!model.deloJePrerada'
              }
            },
            {
              key: 'Delo.Prerada.Originalni_Autor.prezime',
              type: 'input',
              props: {
                type: 'text',
                label: 'Prezime originalnog autora',
                required: true,
              },
              expressions: {
                hide: '!model.deloJePrerada'
              }
            },
            //////////////////////////
            
          ], 
        },
        {
          props: { label: 'Prilozi' },
          fieldGroup: [
            {
              key: 'opisPrilog',
              type: 'file',
              props: {
                label: 'Prilog opisa dela'
              }
            },
            {
              key: 'primerPrilog',
              type: 'file',
              props: {
                label: 'Prilog primera dela'
              }
            },
            {
              key: 'punomocjePrilog',
              type: 'file',
              props: {
                label: 'Punomocje'
              },
              expressions: {
                hide: '!model.postojiPunomocnik'
              }
            },
            {
              key: 'uplataPrilog',
              type: 'file',
              props: {
                label: 'Uplata'
              }
            }
          ]
        }
      ],
    },
  ];

  submit() {
    let model = JSON.parse(JSON.stringify(this.model));
    let json = JSON.stringify(model);
    delete model.podnosilacJeAutor
    delete model.deloJePrerada
    delete model.postojiPunomocnik
    let tipPodnosioca =  model.tipPodnosioca
    delete model.tipPodnosioca
    console.log(model);
    
    // model = this.resolveTypes(model, tipPodnosioca)
    // const xml = js2xmlparser.parse("Zahtevi", model);
    // console.log(xml);
    // this.httpRequestService.post("http://localhost:8081/autorska/add", xml).subscribe(x => {
    //   console.log(x);
    // })
  }

  resolveTypes(model: any, tipPodnosioca: any) {
    if('Autor' in model) {
      delete model.Autor.anoniman
    }
    if('Punomocnik' in model) {
      model.Punomocnik['@'] = {'xsi:type': "TFizicko_Lice"}
    }
    console.log('here 2');
    if(tipPodnosioca == 1) {
      model.Podnosilac['@'] = {'xsi:type': "TPravno_Lice"}
    } else {
      model.Podnosilac['@'] = {'xsi:type': "TFizicko_Lice"}
    }
    console.log('here 3');

    model['Informacije_Zavoda'] = {
      'broj_prijave': 'A-0000-00',
      'datum_podnosenja': '2006-05-05',
      'prilozi': {
        'primer_dela': {
          'putanja': '',
          'naziv_datoteke': ''
        },
        'opis_dela': {
          'putanja': '',
          'naziv_datoteke': ''
        },
        'punomocje': {
          'putanja': '',
          'naziv_datoteke': ''
        },
        'uplata': {
          'putanja': '',
          'naziv_datoteke': ''
        }
      }
    }

    model['Informacije_Sistema'] = {
      'username': 'username',
      'email': 'email'
    }

    model = this.shuffleType(model, tipPodnosioca);


    model = {
      '@': {xmlns:"http://www.zavod.com/Autorska",
                  "xmlns:xsi": "http://www.w3.org/2001/XMLSchema-instance"},
      'Zahtev': model
    }
  
    return model;
  }

  shuffleType(model: any, tipPodnosioca: any) {
    let shuffled: any = {}
    shuffled['Podnosilac'] = model.Podnosilac;
    if (tipPodnosioca == 0) {
      this.shuffleFizickoLice(shuffled, 'Podnosilac')
    }
    shuffled['Delo'] = model.Delo;
    if('Autor' in model) {
      shuffled['Autor'] = model.Autor
      this.shuffleFizickoLice(shuffled, 'Autor')
    }

    shuffled['Informacije_Zavoda'] = model.Informacije_Zavoda
    if('Punomocnik' in model) {
      shuffled['Punomocnik'] = model.Punomocnik
      this.shuffleFizickoLice(shuffled, 'Punomocnik')
    }
    shuffled['Informacije_Sistema'] = model.Informacije_Sistema
    return shuffled
  }

  shuffleFizickoLice(model: any, fieldName: string) {
    let shuffled: any = {};
    shuffled.ime = model[fieldName].ime;
    if('@' in model[fieldName]) {
      shuffled['@'] = model[fieldName]['@']; 
    }
    if('telefon' in model[fieldName]) {
      shuffled.telefon = model[fieldName].telefon
    }
    if('email' in model[fieldName]) {
      shuffled.email = model[fieldName].email
    }
    if('pseudonim' in model[fieldName]) {
      shuffled.pseudonim = model[fieldName].pseudonim
    }
    shuffled.prezime = model[fieldName].prezime;
    shuffled.adresa = model[fieldName].adresa
    shuffled.drzavljanstvo = model[fieldName].drzavljanstvo
    if('godina_smrti' in model[fieldName]) {
      shuffled.godina_smrti = model[fieldName].godina_smrti
    }
    model[fieldName] = shuffled;
  }


}

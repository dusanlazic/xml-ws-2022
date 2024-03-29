import { HttpClient, HttpEvent, HttpHeaders, HttpParams, HttpRequest } from '@angular/common/http';
import { AfterViewInit, Component, OnInit } from '@angular/core';
import { FormGroup, RequiredValidator } from '@angular/forms';
import { FormlyFormOptions, FormlyFieldConfig } from '@ngx-formly/core';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { ParserService } from 'src/services/parser.service';
import { AutoriNoviZahtevService } from 'src/services/util/autori-novi-zahtev.service';
import { HttpRequestService, autorskaBackend } from 'src/services/util/http-request.service';
import { LocalStorageService } from 'src/services/util/local-storage.service';
var _ = require('lodash');


let autorField = [
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
]



@Component({
  selector: 'app-novi-zahtev',
  templateUrl: './novi-zahtev-autorska.component.html',
  styleUrls: ['./novi-zahtev-autorska.component.sass']
})
export class NoviZahtevAutorskaComponent implements OnInit, AfterViewInit {

	ngOnInit(): void {
	}

  constructor(
    private httpRequestService: HttpRequestService, 
    private autoriService: AutoriNoviZahtevService, 
    private parser: ParserService, 
    private http: HttpClient,
    private localStorageService: LocalStorageService,
    private toastr: ToastrService) { }

  ngAfterViewInit(): void {
    this.changed()

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
      type: 'stepper-autorska',
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
                  {value: 0, label:'Fizičko `'},
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
                hide: 'model.podnosilacJeAutor',
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
                label: 'Prilog opisa dela',
                fileid: 'opisid'
              }
            },
            {
              key: 'primerPrilog',
              type: 'file',
              props: {
                label: 'Prilog primera dela',
                fileid: 'primerid'
              }
            },
            {
              key: 'punomocjePrilog',
              type: 'file',
              props: {
                label: 'Punomoćje',
                fileid: 'punomocjeid'
              },
              expressions: {
                hide: '!model.postojiPunomocnik'
              }
            },
            {
              key: 'uplataPrilog',
              type: 'file',
              props: {
                label: 'Uplata',
                fileid: 'uplataid'
              }
            }
          ]
        }
      ],
    },
  ];

  baseFields = _.cloneDeep(this.fields);
  first: boolean = true;

  changed() {
    this.options.fieldChanges?.subscribe((change) => {
      if(change.field.key === 'Autor.anoniman') {
        if (this.model.Autor.anoniman === false) {
          this.autoriService.show.next(true);
          if (this.autoriService.number.value === 0) {
            this.autoriService.increaseNumber()
          }
        } else {
          this.autoriService.show.next(false);
        }
      }
    });
    
    this.autoriService.number.asObservable().subscribe(x => {
      let nesto = _.cloneDeep(this.baseFields)
      let group = nesto[0].fieldGroup![1].fieldGroup!;

      for (let i = 0; i < x; i++) {
        let key = this.autoriService.number;
        let newFields = this.renameFields(autorField, i + 1);
        group.push(...newFields);
      }
      if(!this.first) {
        this.autoriService.setStep(1);
      }
      this.fields = nesto;
      this.first = false;
    })
  }

  renameFields(fields: any, key: any) {
    let newFields = _.cloneDeep(fields);
    newFields.forEach((field: any) => {
      field.key = field.key + key;
      field.props.label = field.props.label + " " + key;
      if(field.fieldGroup) {
        field.fieldGroup = this.renameFields(field.fieldGroup, key)
      }
    });
  
    return newFields;
  }


  submit() {
    
    
    let model = JSON.parse(JSON.stringify(this.model));
    let json = JSON.stringify(model);
    
    
    
    
    let autorJePodnosilac = model.podnosilacJeAutor ? true : false
    let autorJeAnoniman = model.Autor.anoniman ? true : false

    delete model.deloJePrerada
    let tipPodnosioca =  model.tipPodnosioca
    delete model.tipPodnosioca
    this.parseAutori(model);

    
    
    model = this.resolveTypes(model, tipPodnosioca)

    delete model.podnosilacJeAutor
    delete model.postojiPunomocnik


    model.Zahtev.Autori = {
      podnosilac_je_autor: autorJePodnosilac,
      autor_je_anoniman: autorJeAnoniman,
      ...model.Zahtev.Autori
    }
    
    

    this.uploadPriloziAndSend(model);


    let xml = this.parser.js2xml(model);
    


    // this.httpRequestService.post("http://localhost:8081/zahtevi/", xml).subscribe(x => {
    //   
    // })
  }

  uploadPriloziAndSend(model: any) {
    // uplata punomocje primer opis
    let uplataInput: any = document.getElementById("uplataid");
    if(!uplataInput.files[0]) {
      this.toastr.error("Morate uneti uplatu");
      return;
    }
    let fileUplata = uplataInput.files[0];


    let primerInput: any = document.getElementById("primerid");
    if(!primerInput.files[0]) {
      this.toastr.error("Morate uneti primer dela");
      return;
    }

    let filePrimer = primerInput.files[0];

    let opisInput: any = document.getElementById("opisid");
    if(!opisInput.files[0]) {
      this.toastr.error("Morate uneti opis dela");
      return;
    }

    let fileOpis = opisInput.files[0];

    let files = [fileUplata, filePrimer, fileOpis];
    let names = ["uplata", "primer_dela", "opis_dela"];

    if(model.postojiPunomocnik) {
      let punomocjeInput: any = document.getElementById("punomocjeid");
      if(!punomocjeInput.files[0]) {
        this.toastr.error("Morate uneti punomoćje");
        return;
      }
      let filePunomocje = punomocjeInput.files[0];

      files.push(filePunomocje);
      names.push("punomocje");
    }

    this.uploadPrilog(model, files, names);

  }

  uploadPrilog(model: any, files: any, names: any, index=0) {
    let current = files[index];
    let currentName = names[index];    
    this.uploadFile(current).subscribe({
      next: (event: any) => {
        let odg = this.parser.xml2js(event);
        
        
        let filename = odg.fileuploadresponse.filename._text
        model.Zahtev.Informacije_Zavoda.prilozi[currentName].putanja = filename;
        model.Zahtev.Informacije_Zavoda.prilozi[currentName].naziv_datoteke = filename.split("/").pop();
        if(index < files.length - 1) {
          this.uploadPrilog(model, files, names, index + 1);
        } else {
          this.sendModel(model);
        }
      }, error(err) {
          
      },
     
    });
  }

  sendModel(model: any) {
    model.Zahtev.Informacije_Zavoda.status_resenja = "NA_CEKANJU";
    let xml = this.parser.js2xml(model);
    
    let that = this;
    this.httpRequestService.post("http://localhost:8081/zahtevi/", xml).subscribe({
      next(value) {
          that.toastr.success("Uspešno ste poslali zahtev");
      },
      error(err) {
          that.toastr.error("Došlo je do greške");
      }
    });
  }


  uploadFile(file: File) {

    let formData = new FormData();
    formData.append('file', file);

    let token = this.localStorageService.get("access_token")

    const options = {
      headers: new HttpHeaders({
        "Authorization": "Bearer " + token
      }),
      reportProgress: true,
    };

    return this.http.post(autorskaBackend + "/dokumenti", formData, { ...options, responseType: "text"});
  }

  parseAutori(model: any) {
    let Autori = []
    let brojAutora = this.autoriService.number.value;
    if (model.Autor.anoniman === false && !model.podnosilacJeAutor) {
      for (let i = 0; i < brojAutora; i++) {
        try {
          let autor = {
            'ime': model.Autor['ime' + (i + 1)],
            'prezime': model.Autor['prezime' + (i + 1)],
            'adresa': model.Autor['adresa' + (i + 1)],
            'drzavljanstvo': model.Autor['drzavljanstvo' + (i + 1)],
            'godina_smrti': model.Autor['godina_smrti' + (i + 1)],
          }
          let autorP: any = this.removeUndefinedProperties(autor);
          Autori.push(autorP);
        } catch (error) {
        }
      }
    }
    model.Autori = Autori;
    delete model.Autor;
  }

  removeUndefinedProperties(obj: { [key: string]: any }) {
    return Object.fromEntries(
      Object.entries(obj).filter(([key, value]) => value !== undefined)
    );
  }
  


  resolveTypes(model: any, tipPodnosioca: any) {
    if('Autor' in model) {
      delete model.Autor.anoniman
    }
    if('Punomocnik' in model) {
      model.Punomocnik['_attributes'] = {'xsi:type': "TFizicko_Lice"}
    }

    if(!model.postojiPunomocnik && model.Punomocnik) {
      delete model.Punomocnik;
    }

    
    if (tipPodnosioca == 1) {
      model.Podnosilac['_attributes'] = {'xsi:type': "TPravno_Lice"}
    } else {
      model.Podnosilac['_attributes'] = {'xsi:type': "TFizicko_Lice"}
    }
    

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

    model['_attributes'] = {xmlns:"http://www.zavod.com/Autorska", "xmlns:xsi": "http://www.w3.org/2001/XMLSchema-instance"}

    model = {
      '_attributes': {xmlns:"http://www.zavod.com/Autorska",
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
    if('Autori' in model) {
      shuffled['Autori'] = {
        'Autor': []
      }
      for(let aut of model.Autori) {
        let shuffledAut = this.shuffleFizickoLiceNoInsert(aut);
        shuffled['Autori']['Autor'].push(shuffledAut);
      }
      shuffled['Autor'] = model.Autor
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
    if('_attributes' in model[fieldName]) {
      shuffled['_attributes'] = model[fieldName]['_attributes']; 
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

  shuffleFizickoLiceNoInsert(model: any) {
    let shuffled: any = {};
    shuffled.ime = model.ime;
    if('_attributes' in model) {
      shuffled['_attributes'] = model['_attributes']; 
    }
    if('telefon' in model) {
      shuffled.telefon = model.telefon
    }
    if('email' in model) {
      shuffled.email = model.email
    }
    if('pseudonim' in model) {
      shuffled.pseudonim = model.pseudonim
    }
    shuffled.prezime = model.prezime;
    shuffled.adresa = model.adresa
    shuffled.drzavljanstvo = model.drzavljanstvo
    if('godina_smrti' in model) {
      shuffled.godina_smrti = model.godina_smrti
    }
    return shuffled;
  }


}

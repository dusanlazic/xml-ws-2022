import { HttpClient, HttpEvent, HttpHeaders, HttpParams, HttpRequest } from '@angular/common/http';
import { AfterViewInit, Component, OnInit } from '@angular/core';
import { FormGroup, RequiredValidator } from '@angular/forms';
import { FormlyFormOptions, FormlyFieldConfig } from '@ngx-formly/core';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { ParserService } from 'src/services/parser.service';
import { AutoriNoviZahtevService } from 'src/services/util/autori-novi-zahtev.service';
import { HttpRequestService, zigBackend } from 'src/services/util/http-request.service';
import { LocalStorageService } from 'src/services/util/local-storage.service';
var _ = require('lodash');


@Component({
  selector: 'app-novi-zahtev',
  templateUrl: './novi-zahtev-zig.component.html',
  styleUrls: ['./novi-zahtev-zig.component.sass']
})
export class NoviZahtevZigComponent implements OnInit, AfterViewInit {

	ngOnInit(): void {
	}

  constructor(
    private httpRequestService: HttpRequestService, 
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
  };

	options: FormlyFormOptions = {};

	fields: FormlyFieldConfig[] = [
	{
      type: 'stepper-zig',
      fieldGroup: [
        {
          props: { label: 'Podaci o podnosiocu' },
          fieldGroup: [
            {
              key: 'tipPodnosioca',
              type: 'radio',
              defaultValue: 0,
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
              key: 'Podnosilac.poslovno_ime',
              type: 'input',
              props: {
                type: 'text',
                label: 'Poslovno ime',
                required: true,
              },
              expressions: {
                hide: 'model.tipPodnosioca == 0'
              }
            },
            {
              key: 'Podnosilac.ime',
              type: 'input',
              props: {
                label: 'Ime',
                required: true,
              },
              expressions: {
                hide: 'model.tipPodnosioca == 1'
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
            {
              key: 'Podnosilac.faks',
              type: 'input',
              props: {
                type: 'text',
                label: 'Faks',
                required: false,
              },
            },
            {
              key: 'Podnosilac.adresa.ulica',
              type: 'input',
              props: {
                type: 'text',
                label: 'Ulica',
                required: true,
              },
            },
            {
              key: 'Podnosilac.adresa.broj',
              type: 'input',
              props: {
                type: 'text',
                label: 'Broj',
                required: true,
              },
            },
            {
              key: 'Podnosilac.adresa.mesto',
              type: 'input',
              props: {
                type: 'text',
                label: 'Mesto',
                required: true,
              },
            },
            {
              key: 'Podnosilac.adresa.drzava',
              type: 'input',
              props: {
                type: 'text',
                label: 'Država',
                required: true,
              },
            },
            {
              key: 'Podnosilac.adresa.postanski_broj',
              type: 'input',
              props: {
                type: 'text',
                label: 'Poštanski broj',
                required: true,
              },
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
              defaultValue: false,
              props: {
                label: 'Prijava preko punomoćnika',
                required: true
              },
            },
            {
              key: 'tipPunomocnika',
              type: 'radio',
              defaultValue: 0,
              props: {
                label: 'Tip lica',
                required: true,
                options: [
                  {value: 0, label:'Fizičko'},
                  {value: 1, label:'Pravno'}
                ]
              },
              expressions: {
                hide: '!model.postojiPunomocnik'
              }
            },
            {
              key: 'Punomocnik.poslovno_ime',
              type: 'input',
              props: {
                type: 'text',
                label: 'Poslovno ime',
                required: true,
              },
              expressions: {
                hide: '!model.postojiPunomocnik || model.tipPunomocnika == 0'
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
                hide: '!model.postojiPunomocnik || model.tipPunomocnika == 1'
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
                hide: '!model.postojiPunomocnik || model.tipPunomocnika == 1'
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
            {
              key: 'Punomocnik.faks',
              type: 'input',
              props: {
                type: 'text',
                label: 'Faks',
                required: false,
              },
              expressions: {
                hide: '!model.postojiPunomocnik'
              }
            },
            {
              key: 'Punomocnik.adresa.ulica',
              type: 'input',
              props: {
                type: 'text',
                label: 'Ulica',
                required: true,
              },
              expressions: {
                hide: '!model.postojiPunomocnik'
              }
            },
            {
              key: 'Punomocnik.adresa.broj',
              type: 'input',
              props: {
                type: 'text',
                label: 'Broj',
                required: true,
              },
              expressions: {
                hide: '!model.postojiPunomocnik'
              }
            },
            {
              key: 'Punomocnik.adresa.mesto',
              type: 'input',
              props: {
                type: 'text',
                label: 'Mesto',
                required: true,
              },
              expressions: {
                hide: '!model.postojiPunomocnik'
              }
            },
            {
              key: 'Punomocnik.adresa.drzava',
              type: 'input',
              props: {
                type: 'text',
                label: 'Država',
                required: true,
              },
              expressions: {
                hide: '!model.postojiPunomocnik'
              }
            },
            {
              key: 'Punomocnik.adresa.postanski_broj',
              type: 'input',
              props: {
                type: 'text',
                label: 'Poštanski broj',
                required: true,
              },
              expressions: {
                hide: '!model.postojiPunomocnik'
              }
            },
          ],
        },
        // ------------------ PODACI O ZAJEDNIČKOM PREDSTAVNIKU ----------------------
        {
          props: { label: 'Podaci o zajedničkom predstavniku' },
          fieldGroup: [
            {
              key: 'postojiPredstavnik',
              type: 'checkbox',
              defaultValue: false,
              props: {
                label: 'Prijava preko zajedničkog predstavnika',
                required: true
              }
            },
            {
              key: 'tipPredstavnika',
              type: 'radio',
              defaultValue: 0,
              props: {
                label: 'Tip lica',
                required: true,
                options: [
                  {value: 0, label:'Fizičko'},
                  {value: 1, label:'Pravno'}
                ]
              },
              expressions: {
                hide: '!model.postojiPredstavnik'
              }
            },
            {
              key: 'Predstavnik.poslovno_ime',
              type: 'input',
              props: {
                type: 'text',
                label: 'Poslovno ime',
                required: true,
              },
              expressions: {
                hide: '!model.postojiPredstavnik || model.tipPredstavnika == 0'
              }
            },
            {
              key: 'Predstavnik.ime',
              type: 'input',
              props: {
                label: 'Ime',
                required: true,
              },
              expressions: {
                hide: '!model.postojiPredstavnik || model.tipPredstavnika == 1'
              }
            },
            {
              key: 'Predstavnik.prezime',
              type: 'input',
              props: {
                label: 'Prezime',
                required: true,
              },
              expressions: {
                hide: '!model.postojiPredstavnik || model.tipPredstavnika == 1'
              }
            },
            {
              key: 'Predstavnik.telefon',
              type: 'input',
              props: {
                type: 'text',
                label: 'Telefon',
                required: false,
              },
              expressions: {
                hide: '!model.postojiPredstavnik'
              }
            },
            {
              key: 'Predstavnik.email',
              type: 'input',
              props: {
                type: 'text',
                label: 'Email',
                required: false,
              },
              expressions: {
                hide: '!model.postojiPredstavnik'
              }
            },
            {
              key: 'Predstavnik.faks',
              type: 'input',
              props: {
                type: 'text',
                label: 'Faks',
                required: false,
              },
              expressions: {
                hide: '!model.postojiPredstavnik'
              }
            },
            {
              key: 'Predstavnik.adresa.ulica',
              type: 'input',
              props: {
                type: 'text',
                label: 'Ulica',
                required: true,
              },
              expressions: {
                hide: '!model.postojiPredstavnik'
              }
            },
            {
              key: 'Predstavnik.adresa.broj',
              type: 'input',
              props: {
                type: 'text',
                label: 'Broj',
                required: true,
              },
              expressions: {
                hide: '!model.postojiPredstavnik'
              }
            },
            {
              key: 'Predstavnik.adresa.mesto',
              type: 'input',
              props: {
                type: 'text',
                label: 'Mesto',
                required: true,
              },
              expressions: {
                hide: '!model.postojiPredstavnik'
              }
            },
            {
              key: 'Predstavnik.adresa.drzava',
              type: 'input',
              props: {
                type: 'text',
                label: 'Država',
                required: true,
              },
              expressions: {
                hide: '!model.postojiPredstavnik'
              }
            },
            {
              key: 'Predstavnik.adresa.postanski_broj',
              type: 'input',
              props: {
                type: 'text',
                label: 'Poštanski broj',
                required: true,
              },
              expressions: {
                hide: '!model.postojiPredstavnik'
              }
            },
          ],
        },
        {
          props: { label: 'Podaci o žigu' },
          fieldGroup: [
            {
              key: 'Zig.tip_ziga',
              type: 'radio',
              props: {
                options: [
                  {value: 'individualni', label:'Individualni'},
                  {value: 'kolektivni', label:'Kolektivni'},
                  {value: 'garantni', label:'Garantni'}
                ],
                label: 'Tip žiga',
                required: true,
              },
            },
            {
              key: 'Zig.tip_znaka',
              type: 'radio',
              props: {
                options: [
                  {value: 'verbalni', label:'Verbalni'},
                  {value: 'graficki', label:'Graficki'},
                  {value: 'kombinovani', label:'Kombinovani'},
                  {value: 'trodimenzionalni', label:'Trodimenzionalni'},
                  {value: 'druga', label:'Druga vrsta'},
                ],
                label: 'Tip znaka',
                required: true,
              },
            },
            {
              key: 'Zig.boja',
              type: 'input',
              props: {
                type: 'text',
                label: 'Naznačenje boje, odnosno boja iz kojih se znak sastoji',
                required: true,
              },
            },
            {
              key: 'Zig.transliteracija',
              type: 'input',
              props: {
                type: 'text',
                label: 'Transliteracija znaka',
                required: false,
              },
            },
            {
              key: 'Zig.prevod',
              type: 'input',
              props: {
                type: 'text',
                label: 'Prevod znaka',
                required: false,
              },
            },
            {
              key: 'Zig.opis',
              type: 'input',
              props: {
                type: 'text',
                label: 'Opis znaka',
                required: true,
              },
            },
            {
              key: 'Zig.klase_robe',
              type: 'input',
              props: {
                type: 'text',
                label: 'Navesti brojeve klasa robe i usluga prema Ničanskoj klasifikaciji (brojeve odvojiti zarezom)',
                required: true,
              },
            },
            {
              key: 'Zig.pravo_prvenstva',
              type: 'input',
              props: {
                type: 'text',
                label: 'Zatraženo pravo prvenstva i osnov',
                required: true,
              },
            },
          ], 
        },
        {
          props: { label: 'Prilozi' },
          fieldGroup: [
            {
              key: 'dostavljanjePunomocja',
              type: 'radio',
              props: {
                label: 'Dostavljanje punomoćja',
                required: true,
                options: [
                  {value: 0, label:'Dostavljeno u prilogu ove forme'},
                  {value: 1, label:'Ranije priloženo'},
                  {value: 2, label:'Punomoćje će biti naknadno dostavljeno'}
                ]
              },
              expressions: {
                hide: '!model.postojiPunomocnik'
              }
            },
            {
              key: 'primerakZnakaPrilog',
              type: 'file',
              props: {
                label: 'Primerak znaka',
                fileid: 'primerakZnakaid'
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
                hide: '!model.postojiPunomocnik || model.dostavljanjePunomocja != 0'
              }
            },
            {
              key: 'aktPrilog',
              type: 'file',
              props: {
                label: 'Opšti akt o kolektivnom žigu / žigu garancije',
                fileid: 'aktId'
              },
            },
            {
              key: 'pravoPrvenstvaPrilog',
              type: 'file',
              props: {
                label: 'Dokaz o pravu prvenstva',
                fileid: 'pravoPrvenstvaid'
              },
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
    console.log(model);
    
    let tipPodnosioca =  model.tipPodnosioca;
    delete model.tipPodnosioca;
    let tipPredstavnika =  model.tipPredstavnika;
    delete model.tipPredstavnika;
    let tipPunomocnika =  model.tipPunomocnika;
    delete model.tipPunomocnika;
    delete model.postojiPredstavnik;
    delete model.postojiPunomocnik;

    console.log(model);
    
    model = this.resolveTypes(model, tipPodnosioca, tipPredstavnika, tipPunomocnika)
    console.log(model);

    delete model.dostavljanjePunomocja;
    this.uploadPriloziAndSend(model);


    let xml = this.parser.js2xml(model);
    console.log(xml);


    // this.httpRequestService.post("http://localhost:8081/zahtevi/", xml).subscribe(x => {
    //   console.log(x);
    // })
  }

  uploadPriloziAndSend(model: any) {
    let files = [];
    let names = [];

    let primerakZnakaInput: any = document.getElementById("primerakZnakaid");
    let filePrimerakZnaka = primerakZnakaInput.files[0];
    if (filePrimerakZnaka) {
      files.push(filePrimerakZnaka)
      names.push("primerak_znaka")
    }

    let aktInput: any = document.getElementById("aktId");
    let fileAkt = aktInput.files[0];
    if (fileAkt) {
      files.push(fileAkt)
      names.push("opsti_akt_o_kolektivnom_zigu_garancije")
    }

    let pravoPrvenstvaInput: any = document.getElementById("pravoPrvenstvaid");
    let filePravoPrvenstva = pravoPrvenstvaInput.files[0];
    if (filePravoPrvenstva) {
      files.push(filePravoPrvenstva)
      names.push("dokaz_o_pravu_prvenstva")
    }

    let uplataInput: any = document.getElementById("uplataid");
    let fileUplata = uplataInput.files[0];
    if (fileUplata) {
      files.push(fileUplata)
      names.push("dokaz_o_uplati_takse")
    }

    if(model.postojiPunomocnik) {
      let punomocjeInput: any = document.getElementById("punomocjeid");
      let filePunomocje = punomocjeInput.files[0];
      if (filePunomocje) {
        files.push(filePunomocje);
        names.push("punomocje");
      }
    }

    this.uploadPrilog(model, files, names);
  }

  uploadPrilog(model: any, files: any, names: any, index=0) {
    let current = files[index];
    let currentName = names[index];    
    this.uploadFile(current).subscribe({
      next: (event: any) => {
        let odg = this.parser.xml2js(event);
        console.log("OJSAAAAA");
        console.log(odg);
        console.log(model);
        let filename = odg.fileuploadresponse.filename._text
        model.Zahtev.Informacije_Zavoda.prilozi[currentName].putanja = filename;
        model.Zahtev.Informacije_Zavoda.prilozi[currentName].naziv_datoteke = filename.split("/").pop();
        if(index < files.length - 1) {
          this.uploadPrilog(model, files, names, index + 1);
        } else {
          this.sendModel(model);
        }
      }, error(err) {
          console.log(err);
      },
     
    });
  }

  sendModel(model: any) {
    model.Zahtev.Informacije_Zavoda.status_resenja = "NA_CEKANJU";
    let xml = this.parser.js2xml(model);
    console.log(xml);
    let that = this;
    this.httpRequestService.post("http://localhost:8082/zahtevi/", xml).subscribe({
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

    return this.http.post(zigBackend + "/dokumenti", formData, { ...options, responseType: "text"});
  }

  removeUndefinedProperties(obj: { [key: string]: any }) {
    return Object.fromEntries(
      Object.entries(obj).filter(([key, value]) => value !== undefined)
    );
  }
  


  resolveTypes(model: any, tipPodnosioca: any, tipPredstavnika: any, tipPunomocnika: any) {
    if (tipPodnosioca == 1) {
      model.Podnosilac['_attributes'] = {'xsi:type': "TPravno_Lice"}
    } else {
      model.Podnosilac['_attributes'] = {'xsi:type': "TFizicko_Lice"}
    }

    if (model.postojiPredstavnik) {
      if (tipPredstavnika == 1) {
        model.Predstavnik['_attributes'] = {'xsi:type': "TPravno_Lice"}
      } else {
        model.Predstavnik['_attributes'] = {'xsi:type': "TFizicko_Lice"}
      }
    } else {
      delete model.Punomocnik;
    }

    if (model.postojiPunomocnik) {
      if (tipPunomocnika == 1) {
        model.Punomocnik['_attributes'] = {'xsi:type': "TPravno_Lice"}
      } else {
        model.Punomocnik['_attributes'] = {'xsi:type': "TFizicko_Lice"}
      }
    } else {
      delete model.Predstavnik;
    }

    model['Informacije_Zavoda'] = {
      'broj_prijave': 'A-0000-00',
      'datum_podnosenja': '2006-05-05',
      'prilozi': {
        'primerak_znaka': {
          'putanja': '',
          'naziv_datoteke': ''
        },
        'punomocje': {
          'putanja': '',
          'naziv_datoteke': ''
        },
        'opsti_akt_o_kolektivnom_zigu_garancije': {
          'putanja': '',
          'naziv_datoteke': ''
        },
        'dokaz_o_pravu_prvenstva': {
          'putanja': '',
          'naziv_datoteke': ''
        },
        'dokaz_o_uplati_takse': {
          'putanja': '',
          'naziv_datoteke': ''
        },
        'generalno_punomocje_ranije_prilozeno': model.dostavljanjePunomocja == 1,
        'punomocje_ce_biti_naknadno_dostavljeno': model.dostavljanjePunomocja == 2
      }
    }

    model['Informacije_Sistema'] = {
      'username': 'username',
      'email': 'email'
    }

    model = this.shuffleType(model, tipPodnosioca, tipPredstavnika, tipPunomocnika);

    model['_attributes'] = {xmlns:"http://www.zavod.com/Zig", "xmlns:xsi": "http://www.w3.org/2001/XMLSchema-instance"}

    model = {
      '_attributes': {xmlns:"http://www.zavod.com/Zig",
                  "xmlns:xsi": "http://www.w3.org/2001/XMLSchema-instance"},
      'Zahtev': model
    }
  
    return model;
  }

  shuffleType(model: any, tipPodnosioca: any, tipPredstavnika: any, tipPunomocnika: any) {
    let shuffled: any = {}
    shuffled['Podnosilac'] = model.Podnosilac;
    if (tipPodnosioca == 0) {
      this.shuffleFizickoLice(shuffled, 'Podnosilac')
    } else {
      this.shufflePoslovnoLice(shuffled, 'Podnosilac')
    }

    if ('Punomocnik' in model) {
      shuffled['Punomocnik'] = model.Punomocnik;
      if (tipPunomocnika == 0) {
        this.shuffleFizickoLice(shuffled, 'Punomocnik')
      } else {
        this.shufflePoslovnoLice(shuffled, 'Punomocnik')
      }
    }

    if ('Predstavnik' in model) {
      shuffled['Predstavnik'] = model.Predstavnik;
      if (tipPredstavnika == 0) {
        this.shuffleFizickoLice(shuffled, 'Predstavnik')
      } else {
        this.shufflePoslovnoLice(shuffled, 'Predstavnik')
      }
    }

    let shuffledZig: any = {};
    shuffledZig = {
      'tip_ziga': model.Zig.tip_ziga,
      'tip_znaka': model.Zig.tip_znaka,
      'boja': model.Zig.boja,
      'transliteracija': model.Zig.transliteracija,
      'prevod': model.Zig.prevod,
      'opis': model.Zig.opis,
      'klase_robe': {
        'klasa':  model.Zig.klase_robe.split(",")
      },
      'pravo_prvenstva': model.Zig.pravo_prvenstva
    }
    shuffled['Zig'] = shuffledZig

    shuffled['Informacije_Zavoda'] = model.Informacije_Zavoda
    shuffled['Informacije_Sistema'] = model.Informacije_Sistema
    return shuffled
  }

  shuffleFizickoLice(model: any, fieldName: string) {
    let shuffled: any = {};
    shuffled.adresa = {};

    shuffled.adresa = {
      ulica: model[fieldName].adresa.ulica,
      broj: model[fieldName].adresa.broj,
      mesto: model[fieldName].adresa.mesto,
      drzava: model[fieldName].adresa.drzava,
      postanski_broj: model[fieldName].adresa.postanski_broj
    }

    if('_attributes' in model[fieldName]) {
      shuffled['_attributes'] = model[fieldName]['_attributes']; 
    }
    if('telefon' in model[fieldName]) {
      shuffled.telefon = model[fieldName].telefon
    }
    if('email' in model[fieldName]) {
      shuffled.email = model[fieldName].email
    }
    if('faks' in model[fieldName]) {
      shuffled.faks = model[fieldName].faks
    }
    shuffled.ime = model[fieldName].ime;
    shuffled.prezime = model[fieldName].prezime;
    model[fieldName] = shuffled;
  }

  shufflePoslovnoLice(model: any, fieldName: string) {
    let shuffled: any = {};
    shuffled.adresa.ulica = model[fieldName].adresa.ulica;
    shuffled.adresa.broj = model[fieldName].adresa.broj;
    shuffled.adresa.mesto = model[fieldName].adresa.mesto;
    shuffled.adresa.drzava = model[fieldName].adresa.drzava;
    shuffled.adresa.postanski_broj = model[fieldName].adresa.postanski_broj
    if('_attributes' in model[fieldName]) {
      shuffled['_attributes'] = model[fieldName]['_attributes']; 
    }
    if('telefon' in model[fieldName]) {
      shuffled.telefon = model[fieldName].telefon
    }
    if('email' in model[fieldName]) {
      shuffled.email = model[fieldName].email
    }
    if('faks' in model[fieldName]) {
      shuffled.faks = model[fieldName].faks
    }
    shuffled.poslovno_ime = model[fieldName].poslovno_ime;
    model[fieldName] = shuffled;
  }


}

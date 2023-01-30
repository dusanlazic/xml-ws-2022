import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit, SimpleChanges } from '@angular/core';
import { HttpRequestService, autorskaBackend } from 'src/services/util/http-request.service';
import { saveAs } from 'file-saver';
var _ = require('lodash');


@Component({
  selector: 'app-zahtev-autorksa-content',
  templateUrl: './zahtev-autorksa-content.component.html',
  styleUrls: ['./zahtev-autorksa-content.component.sass']
})
export class ZahtevAutorksaContentComponent implements OnInit {

  @Input() in: any;
  zahtev: any;

  constructor(
    private httpRequestService: HttpRequestService,
    private http: HttpClient
    ) { 

  }
  

  ngOnInit(): void {
  }


  ngOnChanges(changes: SimpleChanges): void {
    if(!this.in) return;
    this.zahtev = _.cloneDeep(this.in.zahtev);
    console.log(this.zahtev);
  }

  exportHTML() {
    this.httpRequestService.get(autorskaBackend + "/zahtevi/export/" + this.zahtev.informacije_zavoda.broj_prijave._text  + ".xhtml").subscribe((data: any) => {
        console.log(data);
        let blob = new Blob([data], {type: 'text/html'});
        let url = window.URL.createObjectURL(blob);
        window.open(url);
      }
    );
  }

  exportJSON() {
    this.httpRequestService.get(autorskaBackend + "/zahtevi/export/" + this.zahtev.informacije_zavoda.broj_prijave._text  + ".json").subscribe((data: any) => {
        console.log(data);
        let blob = new Blob([data], {type: 'application/json'});
        saveAs(blob, this.zahtev.informacije_zavoda.broj_prijave._text + ".json");
      }
    );
  }
  
  exportRDF() {
    this.httpRequestService.get(autorskaBackend + "/zahtevi/export/" + this.zahtev.informacije_zavoda.broj_prijave._text + ".rdf").subscribe((data: any) => {
        console.log(data);
        let blob = new Blob([data], {type: 'application/rdf+xml'});
        saveAs(blob, this.zahtev.informacije_zavoda.broj_prijave._text + ".rdf");
      }
    );
  }

  exportPDF() {
    this.http.get(autorskaBackend + "/zahtevi/export/" + this.zahtev.informacije_zavoda.broj_prijave._text + ".pdf", {responseType: 'blob'}).subscribe((data: any) => {
      console.log(data);
      let blob = new Blob([data], {type: 'application/pdf'});
      saveAs(blob, this.zahtev.informacije_zavoda.broj_prijave._text + ".pdf");
    });
  }

}

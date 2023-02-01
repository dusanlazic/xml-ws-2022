import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { HttpRequestService, zigBackend } from 'src/services/util/http-request.service';
var _ = require('lodash');
import { saveAs } from 'file-saver';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-zahtev-zig-content',
  templateUrl: './zahtev-zig-content.component.html',
  styleUrls: ['./zahtev-zig-content.component.sass']
})
export class ZahtevZigContentComponent implements OnInit, OnChanges {


  @Input() in: any;
  zahtev: any;

  constructor(
    private httpRequestService: HttpRequestService,
    private http: HttpClient
    ) { 

  }
  

  ngOnInit(): void {
  }

  download(url: any) {
    // get the file extension from url
    let ext = url.split('.').pop();
    let filename = url.split('/').pop();

    // method that downloads the file from url that is of any type and saves it to the local file system
    
    this.http.get(url, {responseType: 'blob'}).subscribe((data: any) => {
      console.log(data);
      // set blob type based on the file extension
      let blob = new Blob([data], {type: 'application/' + ext});
      // save the file to the local file system
      saveAs(blob, filename);
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(!this.in) return;
    this.zahtev = _.cloneDeep(this.in.zahtev);
    console.log(this.zahtev);
  }

  exportHTML() {
    this.httpRequestService.get(zigBackend + "/zahtevi/export/" + this.zahtev.informacije_zavoda.broj_prijave._text  + ".xhtml").subscribe((data: any) => {
        console.log(data);
        let blob = new Blob([data], {type: 'text/html'});
        let url = window.URL.createObjectURL(blob);
        window.open(url);
      }
    );
  }

  exportJSON() {
    this.httpRequestService.get(zigBackend + "/zahtevi/export/" + this.zahtev.informacije_zavoda.broj_prijave._text  + ".json").subscribe((data: any) => {
        console.log(data);
        let blob = new Blob([data], {type: 'application/json'});
        saveAs(blob, this.zahtev.informacije_zavoda.broj_prijave._text + ".json");
      }
    );
  }
  
  exportRDF() {
    this.httpRequestService.get(zigBackend + "/zahtevi/export/" + this.zahtev.informacije_zavoda.broj_prijave._text + ".rdf").subscribe((data: any) => {
        console.log(data);
        let blob = new Blob([data], {type: 'application/rdf+xml'});
        saveAs(blob, this.zahtev.informacije_zavoda.broj_prijave._text + ".rdf");
      }
    );
  }

  exportPDF() {
    this.http.get(zigBackend + "/zahtevi/export/" + this.zahtev.informacije_zavoda.broj_prijave._text + ".pdf", {responseType: 'blob'}).subscribe((data: any) => {
      console.log(data);
      let blob = new Blob([data], {type: 'application/pdf'});
      saveAs(blob, this.zahtev.informacije_zavoda.broj_prijave._text + ".pdf");
    });
  }

}

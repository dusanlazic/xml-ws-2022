import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { HttpRequestService, zigBackend } from 'src/services/util/http-request.service';
var _ = require('lodash');
import { saveAs } from 'file-saver';
import { HttpClient } from '@angular/common/http';
import { AuthService } from 'src/services/auth/auth.service';
import { User } from 'src/model/user';

@Component({
  selector: 'app-zahtev-zig-content',
  templateUrl: './zahtev-zig-content.component.html',
  styleUrls: ['./zahtev-zig-content.component.sass']
})
export class ZahtevZigContentComponent implements OnInit, OnChanges {


  @Input() in: any;
  zahtev: any;

  canExport: boolean = false;

  constructor(
    private httpRequestService: HttpRequestService,
    private http: HttpClient,
    private authService: AuthService
    ) { 

  }
  
  ngOnInit(): void {
    this.authService.getLoggedUser().subscribe((user) => {
      this.canExport = user?.uloga == "sluzbenik";
    })
  }

  download(url: any) {
    // get the file extension from url
    let ext = url.split('.').pop();
    let filename = url.split('/').pop();

    // method that downloads the file from url that is of any type and saves it to the local file system
    
    this.http.get(url, {responseType: 'blob'}).subscribe((data: any) => {
      // set blob type based on the file extension
      let blob = new Blob([data], {type: 'application/' + ext});
      // save the file to the local file system
      saveAs(blob, filename);
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(!this.in) return;
    this.zahtev = _.cloneDeep(this.in.zahtev);
    if(this.zahtev.zig.klase_robe.klasa && !Array.isArray(this.zahtev.zig.klase_robe.klasa)) {
      this.zahtev.zig.klase_robe.klasa = [this.zahtev.zig.klase_robe.klasa];
    }
    console.log(this.zahtev);
  }

  exportHTML() {
    this.httpRequestService.get(zigBackend + "/zahtevi/export/" + this.zahtev.informacije_zavoda.broj_prijave._text  + ".xhtml").subscribe((data: any) => {
        let blob = new Blob([data], {type: 'text/html'});
        let url = window.URL.createObjectURL(blob);
        window.open(url);
      }
    );
  }

  exportJSON() {
    this.httpRequestService.get(zigBackend + "/zahtevi/export/" + this.zahtev.informacije_zavoda.broj_prijave._text  + ".json").subscribe((data: any) => {
        let blob = new Blob([data], {type: 'application/json'});
        saveAs(blob, this.zahtev.informacije_zavoda.broj_prijave._text + ".json");
      }
    );
  }
  
  exportRDF() {
    this.httpRequestService.get(zigBackend + "/zahtevi/export/" + this.zahtev.informacije_zavoda.broj_prijave._text + ".rdf").subscribe((data: any) => {
        let blob = new Blob([data], {type: 'application/rdf+xml'});
        saveAs(blob, this.zahtev.informacije_zavoda.broj_prijave._text + ".rdf");
      }
    );
  }

  exportPDF() {
    this.http.get(zigBackend + "/zahtevi/export/" + this.zahtev.informacije_zavoda.broj_prijave._text + ".pdf", {responseType: 'blob'}).subscribe((data: any) => {
      let blob = new Blob([data], {type: 'application/pdf'});
      saveAs(blob, this.zahtev.informacije_zavoda.broj_prijave._text + ".pdf");
    });
  }

  humanReadable(broj_prijave: string) {
    return broj_prijave
      .replace("Z", "Ž")
      .replace("-", " ")
      .replace("-", "/");
  }

  formatBrojKlasa() : String {
    let brojKlasa = this.zahtev.zig.klase_robe.klasa.length;
    if (brojKlasa > 3) {
      return "3+" + Math.max(0, brojKlasa - 3);
    } else {
      return brojKlasa;
    }
  }

}

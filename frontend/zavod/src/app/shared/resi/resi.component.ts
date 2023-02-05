import { Component, Input, OnInit, SimpleChanges } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/model/user';
import { AuthService } from 'src/services/auth/auth.service';
import { ParserService } from 'src/services/parser.service';
import { HttpRequestService, autorskaBackend, zigBackend } from 'src/services/util/http-request.service';
var _ = require('lodash');

@Component({
  selector: 'app-resi',
  templateUrl: './resi.component.html',
  styleUrls: ['./resi.component.sass']
})
export class ResiComponent implements OnInit {

  @Input() in: any;
  zahtev: any;

  status : boolean = true;
  loggedUser: User | undefined;
  obrazlozenje: string = "";
  sluzbenikIme: string = "";
  sluzbenikPrezime: string = "";

  date = new Date()
  dateStr = this.date.toISOString().split('T')[0]
  serviceName: string = "";
  backend: string = "";
  podneoOpis: boolean = false;
  podneoPrimer: boolean = false;

  cantModify: boolean = true;


  constructor(private authService: AuthService, 
    private httpService: HttpRequestService, 
    private router: Router, 
    private parser: ParserService,
    private toastr: ToastrService) {
    this.authService.getLoggedUser().subscribe((user: User | undefined) => {
      this.loggedUser = user;
      console.log(this.loggedUser);
      this.serviceName = this.router.url.split("/")[1];
      if(this.serviceName !== 'autorska') {
        this.podneoOpis = true;
        this.podneoPrimer = true;
      }
    });
   }


   ngOnChanges(changes: SimpleChanges): void {
    if(!this.in) return;
    this.zahtev = _.cloneDeep(this.in.zahtev);

    if (this.serviceName == "autorska") {
      this.backend = autorskaBackend;
    } else if (this.serviceName == "zig") {
      this.backend = zigBackend;
    }

    if (this.zahtev.informacije_zavoda.status === 'NA_CEKANJU') {
      this.sluzbenikIme = this.loggedUser?.ime!;
      this.sluzbenikPrezime = this.loggedUser?.prezime!;
      this.cantModify = false;
    } 
    else {
      let that = this;
      this.httpService.get(this.backend + '/resenja/' + this.zahtev.informacije_zavoda.broj_prijave._text).subscribe({
        next: (data) => {
          let resenje = that.parser.xml2js(data);
          console.log(resenje);
          if (!resenje.resenje || !resenje.resenje.odluka) {
            that.cantModify = false;
            console.log();
            
            return;
          }

          that.obrazlozenje = resenje.resenje.odluka.obrazlozenje._text;
          that.status = resenje.resenje.odluka.prihvacen._text === 'true' ? true : false;
          that.podneoOpis = resenje.resenje.odluka.dostavio_opis._text === 'true' ? true : false;
          that.podneoPrimer = resenje.resenje.odluka.dostavio_primer._text === 'true' ? true : false;
          that.sluzbenikIme = resenje.resenje.sluzbenik.ime._text;
          that.sluzbenikPrezime = resenje.resenje.sluzbenik.prezime._text;
          
          that.cantModify = true;

        },
        error: (err) => {
          this.toastr.error("Došlo je do greške", "Greška");
        }
      });
    }
  }

  ngOnInit(): void {
    if (this.serviceName == "autorska") {
      this.backend = autorskaBackend;
    } else if (this.serviceName == "zig") {
      this.backend = zigBackend;
    }
  }

  podnesi() {
    
    let brojZahteva = this.getBrojZahteva();

    let resenje;
    if (this.serviceName == "autorska") {
      resenje = {
        Resenje: {
          odluka: {
            obrazlozenje: this.obrazlozenje,
            prihvacen: this.status,
            dostavio_primer: this.podneoPrimer,
            dostavio_opis: this.podneoOpis,
          }
        }
      }
    } else {
      resenje = {
        Resenje: {
          odluka: {
            obrazlozenje: this.obrazlozenje,
            prihvacen: this.status,
          }
        }
      }
    }

    let parsed = this.parser.js2xml(resenje);
    console.log(parsed);
    
    this.httpService.post(this.backend + "/resenja/" + brojZahteva, parsed).subscribe({
      next: (data) => {
        this.toastr.success("Uspešno ste podneli rešenje", "Uspešno");
      },
      error: (err) => {
        this.toastr.error("Došlo je do greške", "Greška");
      }
    });
  }

  getBrojZahteva() : string {
    return this.router.url.split('/')[4];
  }

}

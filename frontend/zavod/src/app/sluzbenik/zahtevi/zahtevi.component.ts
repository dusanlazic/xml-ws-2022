import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/services/auth/auth.service';
import { ParserService } from 'src/services/parser.service';
import { HttpRequestService, autorskaBackend, zigBackend } from 'src/services/util/http-request.service';

@Component({
  selector: 'app-zahtevi',
  templateUrl: './zahtevi.component.html',
  styleUrls: ['./zahtevi.component.sass']
})
export class ZahteviComponent implements OnInit {

  serviceName: string = ""

  constructor(
    private router: Router,
    private httpRequest: HttpRequestService,
    private parser: ParserService, 
    private authService: AuthService, 
    private toastr: ToastrService) { }


  ngOnInit(): void {
    this.serviceName = this.router.url.split("/")[1];    
    
    this.authService.getLoggedUser().subscribe(
      (user) => {
        if(!user) return; 
        let url: string = "";
        if (this.serviceName === 'autorska') {
          url = autorskaBackend;
        } else if (this.serviceName === 'zig') {
          url = zigBackend;
        } else {
          return;
        }
      
        this.httpRequest.get(url + '/zahtevi/nereseni').subscribe({
          next: (data: any) => {
            let parsedData = this.parser.xml2js(data); 
            if (!Array.isArray(parsedData.zahtevi.zahtev)) {
              parsedData.zahtevi.zahtev = [parsedData.zahtevi.zahtev]
            }
            this.mojiZahtevi = parsedData.zahtevi.zahtev;
          },
          error: (err) => {
            this.toastr.error("Greška!")
          }
        });

      }
    );

  }

  detailed(broj_prijave: string) {
    if(this.serviceName === 'autorska')
      this.router.navigate(['/autorska/sluzbenik/zahtev/' + broj_prijave]);
    else if(this.serviceName === 'zig')
      this.router.navigate(['/zig/sluzbenik/zahtev/' + broj_prijave]);
  }


  mojiZahtevi = [
    {
      informacije_zavoda :{
        broj_prijave: {_text: '...'},
        datum_podnosenja: {_text: '...'},
        status_resenja: {_text: '...'},
      }
    },
    
  ]

}

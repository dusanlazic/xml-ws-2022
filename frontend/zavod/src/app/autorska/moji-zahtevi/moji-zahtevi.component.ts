import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/services/auth/auth.service';
import { ParserService } from 'src/services/parser.service';
import { HttpRequestService, autorskaBackend, zigBackend } from 'src/services/util/http-request.service';

@Component({
  selector: 'app-moji-zahtevi',
  templateUrl: './moji-zahtevi.component.html',
  styleUrls: ['./moji-zahtevi.component.sass']
})
export class MojiZahteviComponent implements OnInit {


  constructor(
    private router: Router,
    private httpRequest: HttpRequestService,
    private parser: ParserService, 
    private authService: AuthService, 
    private toastr: ToastrService) { }


  serviceName : string = "";

  ngOnInit(): void {
    this.serviceName = this.router.url.split('/')[1];
    
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
      
        this.httpRequest.get(url + '/zahtevi/my').subscribe({
          next: (data: any) => {
            let parsedData = this.parser.xml2js(data); 
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
      this.router.navigate(['/autorska/zahtev/' + broj_prijave]);
    else if(this.serviceName === 'zig')
      this.router.navigate(['/zig/zahtev/' + broj_prijave]);
  }

  humanReadable(broj_prijave: string) {
    if(this.serviceName === 'autorska') {
      let c = broj_prijave.lastIndexOf("-");
      if (c != -1)
        return broj_prijave.substring(0, c) + "/" + broj_prijave.substring(c + 1);
      return broj_prijave
    } else if (this.serviceName === 'zig') {
      return broj_prijave
        .replace("Z", "Ž")
        .replace("-", " ")
        .replace("-", "/");
    } else {
      return broj_prijave
    }
  }


  mojiZahtevi = [
    {
      informacije_zavoda :{
        broj_prijave: {_text: 'A-0-23'},
        datum_podnosenja: {_text: '2023-01-01'},
        status_resenja: {_text: 'NA_CEKANJU'},
      }
    },
    
  ]

}

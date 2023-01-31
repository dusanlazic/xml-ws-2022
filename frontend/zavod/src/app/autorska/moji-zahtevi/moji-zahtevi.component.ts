import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/services/auth/auth.service';
import { ParserService } from 'src/services/parser.service';
import { HttpRequestService, autorskaBackend } from 'src/services/util/http-request.service';

@Component({
  selector: 'app-moji-zahtevi',
  templateUrl: './moji-zahtevi.component.html',
  styleUrls: ['./moji-zahtevi.component.sass']
})
export class MojiZahteviComponent implements OnInit {


  constructor(private router: Router, private httpRequest: HttpRequestService, private parser: ParserService, private authService: AuthService, private toastr: ToastrService) { }

  ngOnInit(): void {
    
    this.authService.getLoggedUser().subscribe(
      (user) => {
        if(!user) return; 
      
          this.httpRequest.get(autorskaBackend + '/zahtevi/my').subscribe({
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
    this.router.navigate(['/autorska/zahtev/' + broj_prijave]);
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

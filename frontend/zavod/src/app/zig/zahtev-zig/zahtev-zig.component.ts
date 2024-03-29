import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/model/user';
import { AuthService } from 'src/services/auth/auth.service';
import { ParserService } from 'src/services/parser.service';
import { HttpRequestService, zigBackend } from 'src/services/util/http-request.service';

@Component({
  selector: 'app-zahtev-zig',
  templateUrl: './zahtev-zig.component.html',
  styleUrls: ['./zahtev-zig.component.sass']
})
export class ZahtevZigComponent implements OnInit {

  zahtev: any;

  loggedUser : User | undefined;

  showResenje: boolean = false;

  constructor(
    private authService: AuthService,
    private httpRequestService: HttpRequestService,
    private route: ActivatedRoute,
    private parser: ParserService) {

  }

  ngOnInit(): void {
    this.authService.getLoggedUser().subscribe((user) => {
      this.loggedUser = user;
      this.route.params.subscribe((params) => {
        let brojPrijave = params['broj_prijave'];
        this.httpRequestService.get(zigBackend + '/zahtevi/' +  brojPrijave).subscribe((data: any) => {
          this.zahtev = this.parser.xml2js(data);
          if (this.zahtev.zahtev.informacije_zavoda.status_resenja._text === 'NA_CEKANJU' && this.loggedUser?.uloga === 'gradjanin') {
            this.showResenje = false;
          } else {
            this.showResenje = true;
          }
        })
      });

    });
  }

}

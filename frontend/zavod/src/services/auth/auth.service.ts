import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from 'src/model/user';
import { HttpRequestService, korisinciBackend, zigBackend } from '../util/http-request.service';
var convert = require('xml-js');
import { IndividualConfig, ToastrService } from 'ngx-toastr';
import { LocalStorageService } from '../util/local-storage.service';
import { ParserService } from '../parser.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  constructor(
    private httpRequestService: HttpRequestService,
    private toastrService: ToastrService,
    private localStorage: LocalStorageService,
    private parser: ParserService
  ) {

    let ob = this.httpRequestService.get(zigBackend + "/auth/me")
    ob.subscribe((data: any) => {
      let parsedUser = this.parser.xml2js(data)
      let user = {
        email: parsedUser.korisnik.email._text,
        ime: parsedUser.korisnik.ime._text,
        prezime: parsedUser.korisnik.prezime._text,
        uloga: parsedUser.korisnik.uloga._text,
      }
      this.loggedUser.next(user);
    });

  }

  loggedUser: BehaviorSubject<User | undefined> = new BehaviorSubject<User | undefined>(undefined);


  login(email: string, password: string): Observable<any> {
    
    let kredencijali = {
      kredencijali : {
        email: email,
        lozinka: password
      }
    }
    let parsedKredencijali = convert.js2xml(kredencijali, {compact: true, spaces: 4});
    
    // <kredencijali>
    //     <email>hello@mail1.com</email>
    //     <lozinka>cascaded</lozinka>
    // </kredencijali>
  
    let resp: Observable<any> = this.httpRequestService.post(zigBackend + "/auth/login", parsedKredencijali)
    
    resp.subscribe({
      next: (v) =>  {
        this.loginSuccess(v)
      },
      error: (e) => this.loginFailed(e),
    });

    return resp;
    
  }

  loginSuccess(val: any) {
    let options = {ignoreComment: true, alwaysChildren: true, compact: true}
    let resp = convert.xml2js(val, options)
    this.localStorage.set("access_token", resp.Token.access_token._text);
    let user: User = {
      email: resp.Token.korisnik.email._text,
      ime: resp.Token.korisnik.ime._text,
      prezime: resp.Token.korisnik.prezime._text,
      uloga: resp.Token.korisnik.uloga._text
    }
    this.loggedUser.next(user);

  }

  loginFailed(err: any) {
    
  }

  getLoggedUser(): Observable<User | undefined> {
    return this.loggedUser.asObservable();
  }

  logout() {
    this.localStorage.remove("access_token");
    this.localStorage.clearAll();
    this.loggedUser.next(undefined);
  }

  register(zahtevZaRegistraciju: { email: string; lozinka: string; ime: string; prezime: string; uloga: string; }): Observable<any> {
    let korisnik = {
      Korisnik : zahtevZaRegistraciju
    }
    let that = this;
    let parsedKorisnik = convert.js2xml(korisnik, {compact: true, spaces: 4});
    let resp = this.httpRequestService.post(korisinciBackend + "/auth/register", parsedKorisnik);
    resp.subscribe({
      next(value) {
          that.login(zahtevZaRegistraciju.email, zahtevZaRegistraciju.lozinka);
      },
      error(err) {
          
      },
    })
    return resp;
  }


}

import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/model/user';
import { AuthService } from 'src/services/auth/auth.service';

@Component({
  selector: 'app-pretraga-rezultat-autorska',
  templateUrl: './pretraga-rezultat-autorska.component.html',
  styleUrls: ['./pretraga-rezultat-autorska.component.sass']
})
export class PretragaRezultatAutorskaComponent implements OnInit {

  @Input() rezultat: any


  sluzbenikPrefix = ""

  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
    this.authService.getLoggedUser().subscribe((user: User | undefined) => {
      if(user?.uloga === 'sluzbenik') {
        this.sluzbenikPrefix = 'sluzbenik/'
      }
    });

  }

  detailed() {
    console.log(this.rezultat);
    
    let brojPrijave = this.rezultat.informacije_zavoda.broj_prijave._text;
    this.router.navigate(['autorska/'+this.sluzbenikPrefix+'zahtev/' + brojPrijave])
  }

  humanReadable(broj_prijave: string) {
    let c = broj_prijave.lastIndexOf("-");
    if (c != -1)
      return broj_prijave.substring(0, c) + "/" + broj_prijave.substring(c + 1);
    return broj_prijave
  }
}

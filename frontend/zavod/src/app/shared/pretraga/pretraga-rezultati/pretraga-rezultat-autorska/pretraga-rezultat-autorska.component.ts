import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pretraga-rezultat-autorska',
  templateUrl: './pretraga-rezultat-autorska.component.html',
  styleUrls: ['./pretraga-rezultat-autorska.component.sass']
})
export class PretragaRezultatAutorskaComponent implements OnInit {

  @Input() rezultat: any

  constructor(private router: Router) { }

  ngOnInit(): void {

  }

  detailed() {
    console.log(this.rezultat);
    
    let brojPrijave = this.rezultat.informacije_zavoda.broj_prijave._text;
    this.router.navigate(['autorska/zahtev/' + brojPrijave])
  }


}

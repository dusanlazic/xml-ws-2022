import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pretraga-rezultati',
  templateUrl: './pretraga-rezultati.component.html',
  styleUrls: ['./pretraga-rezultati.component.sass']
})
export class PretragaRezultatiComponent implements OnInit {

  rezultati: {id: string, datum: string, ime: string, prezime: string, naziv: string}[] = [];

  constructor() { }

  ngOnInit(): void {
    this.rezultati.push({id: 'A-0000-00', datum: '2020-01-01', ime: 'Ime', prezime: 'Prezime', naziv: 'Naziv'});
    this.rezultati.push({id: 'A-0000-01', datum: '2020-01-01', ime: 'Ime dva', prezime: 'Prezime dva', naziv: 'Naziv dva'});
  }

}

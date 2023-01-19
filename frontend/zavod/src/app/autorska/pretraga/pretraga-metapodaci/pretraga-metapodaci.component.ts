import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-pretraga-metapodaci',
  templateUrl: './pretraga-metapodaci.component.html',
  styleUrls: ['./pretraga-metapodaci.component.sass']
})
export class PretragaMetapodaciComponent implements OnInit {


  conditions: {name: string, value: string, operator: string}[] = [];

  ops: string[] = [];
  constructor() { }

  ngOnInit(): void {
    this.ops = ['I', 'ILI', 'NE'];
    this.conditions.push({name: 'Ime', value: '', operator: 'I'});
    this.conditions.push({name: 'Ime', value: '', operator: 'ILI'});
  }

  addNewCondition() {
    this.conditions.push({name: '', value: '', operator: 'I'});
  }


}

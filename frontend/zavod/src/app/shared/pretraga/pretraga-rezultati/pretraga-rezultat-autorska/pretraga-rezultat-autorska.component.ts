import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-pretraga-rezultat-autorska',
  templateUrl: './pretraga-rezultat-autorska.component.html',
  styleUrls: ['./pretraga-rezultat-autorska.component.sass']
})
export class PretragaRezultatAutorskaComponent implements OnInit {

  @Input() rezultat: any

  constructor() { }

  ngOnInit(): void {

  }


}

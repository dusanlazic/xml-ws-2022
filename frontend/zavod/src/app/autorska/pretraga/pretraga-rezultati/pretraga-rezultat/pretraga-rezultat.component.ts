import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-pretraga-rezultat',
  templateUrl: './pretraga-rezultat.component.html',
  styleUrls: ['./pretraga-rezultat.component.sass']
})
export class PretragaRezultatComponent implements OnInit {

  @Input() rezultat: any

  constructor() { }

  ngOnInit(): void {

  }


}

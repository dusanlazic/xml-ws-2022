import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
var _ = require('lodash');

@Component({
  selector: 'app-zahtev-zig-content',
  templateUrl: './zahtev-zig-content.component.html',
  styleUrls: ['./zahtev-zig-content.component.sass']
})
export class ZahtevZigContentComponent implements OnInit, OnChanges {


  @Input() in: any;
  zahtev: any;

  constructor() { }
  

  ngOnInit(): void {
  }


  ngOnChanges(changes: SimpleChanges): void {
    if(!this.in) return;
    this.zahtev = _.cloneDeep(this.in.zahtev);
    console.log(this.zahtev);
  }

}

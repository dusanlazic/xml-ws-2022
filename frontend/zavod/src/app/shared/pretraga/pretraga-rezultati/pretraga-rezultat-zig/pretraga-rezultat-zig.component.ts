import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-pretraga-rezultat-zig',
  templateUrl: './pretraga-rezultat-zig.component.html',
  styleUrls: ['./pretraga-rezultat-zig.component.sass']
})
export class PretragaRezultatZigComponent implements OnInit {
  
  @Input() rezultat: any
  
  constructor() { }

  ngOnInit(): void {
  }

}

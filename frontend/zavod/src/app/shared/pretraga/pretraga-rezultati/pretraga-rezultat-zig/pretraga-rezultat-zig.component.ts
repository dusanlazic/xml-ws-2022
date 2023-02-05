import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pretraga-rezultat-zig',
  templateUrl: './pretraga-rezultat-zig.component.html',
  styleUrls: ['./pretraga-rezultat-zig.component.sass']
})
export class PretragaRezultatZigComponent implements OnInit {

  
  @Input() rezultat: any
  
  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  detailed() {
    console.log(this.rezultat);
    
    let brojPrijave = this.rezultat.informacije_zavoda.broj_prijave._text;
    this.router.navigate(['zig/zahtev/' + brojPrijave])
  }

  humanReadable(broj_prijave: string) {
    return broj_prijave
      .replace("Z", "Ž")
      .replace("-", " ")
      .replace("-", "/");
  }
}

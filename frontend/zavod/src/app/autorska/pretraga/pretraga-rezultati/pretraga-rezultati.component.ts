import { Component, OnInit } from '@angular/core';
import { ResultService } from 'src/services/util/result.service';

@Component({
  selector: 'app-pretraga-rezultati',
  templateUrl: './pretraga-rezultati.component.html',
  styleUrls: ['./pretraga-rezultati.component.sass']
})
export class PretragaRezultatiComponent implements OnInit {

  rezultati: any[] = [];

  constructor(private resultService: ResultService) { }

  ngOnInit(): void {
    this.resultService.subscribeToResult().subscribe((data: any) => {
        if(!data || !data.Zahtevi || !data.Zahtevi.Zahtev) {
          this.rezultati = [];
          return;
        }
        if(Array.isArray(data.Zahtevi.Zahtev)) {
          this.rezultati = data.Zahtevi.Zahtev;
        } else {
          this.rezultati = [data.Zahtevi.Zahtev];
        }
    });
  }

}

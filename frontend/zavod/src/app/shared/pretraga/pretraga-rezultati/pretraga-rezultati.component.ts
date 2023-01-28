import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ResultService } from 'src/services/util/result.service';

@Component({
  selector: 'app-pretraga-rezultati',
  templateUrl: './pretraga-rezultati.component.html',
  styleUrls: ['./pretraga-rezultati.component.sass']
})
export class PretragaRezultatiComponent implements OnInit {

  rezultati: any[] = [];
  serviceName: string = "";

  constructor(private resultService: ResultService, private router: Router) { 
    this.serviceName = this.router.url.split("/")[1];
  }

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

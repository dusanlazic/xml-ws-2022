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
        if(!data || !data.zahtevi || !data.zahtevi.zahtev) {
          this.rezultati = [];
          return;
        }
        if(Array.isArray(data.zahtevi.zahtev)) {
          this.rezultati = data.zahtevi.zahtev;
        } else {
          this.rezultati = [data.zahtevi.zahtev];
        }
    });
  }

}

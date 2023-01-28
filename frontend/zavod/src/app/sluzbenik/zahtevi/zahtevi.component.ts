import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-zahtevi',
  templateUrl: './zahtevi.component.html',
  styleUrls: ['./zahtevi.component.sass']
})
export class ZahteviComponent implements OnInit {

  serviceName: string = ""

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.serviceName = this.router.url.split("/")[1];    
  }

}

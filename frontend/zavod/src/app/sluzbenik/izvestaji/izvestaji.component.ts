import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-izvestaji',
  templateUrl: './izvestaji.component.html',
  styleUrls: ['./izvestaji.component.sass']
})
export class IzvestajiComponent implements OnInit {

  serviceName: string = ""

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.serviceName = this.router.url.split("/")[1];    
  }

}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.sass']
})
export class IndexComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  accessAutorska() {
    this.router.navigate(['/autorska']);
  }

  accessZig() {
    this.router.navigate(['/autorska']);
  }

}

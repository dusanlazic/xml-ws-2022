import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { Router } from '@angular/router';
import { AuthService } from 'src/services/auth/auth.service';


@Component({
  selector: 'app-autorska',
  templateUrl: './autorska.component.html',
  styleUrls: ['./autorska.component.sass']
})
export class AutorskaComponent {

  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
  }

  // @ViewChild('sidenav') sidenav: MatSidenav;
  isExpanded = true;
  showSubmenu: boolean = false;
  isShowing = false;
  showSubSubMenu: boolean = false;
  showComponent: number = 0;

  mouseenter() {
    if (!this.isExpanded) {
      this.isShowing = true;
    }
  }

  mouseleave() {
    if (!this.isExpanded) {
      this.isShowing = false;
    }
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/'])
  }

}

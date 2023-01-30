import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/services/auth/auth.service';

@Component({
  selector: 'app-sluzbenik',
  templateUrl: './sluzbenik.component.html',
  styleUrls: ['./sluzbenik.component.sass']
})
export class SluzbenikComponent implements OnInit {


  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/'])
  }

}

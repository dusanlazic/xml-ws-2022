import { Component, Input, OnInit } from '@angular/core';
import { User } from 'src/model/user';
import { AuthService } from 'src/services/auth/auth.service';

@Component({
  selector: 'app-zahtev-zig',
  templateUrl: './zahtev-zig.component.html',
  styleUrls: ['./zahtev-zig.component.sass']
})
export class ZahtevZigComponent implements OnInit {


  @Input() zahtev: any;
  loggedUser : User | undefined;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.authService.getLoggedUser().subscribe((user) => {
      this.loggedUser = user;
    });
  }

  

}

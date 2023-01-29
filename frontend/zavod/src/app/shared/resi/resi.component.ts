import { Component, OnInit } from '@angular/core';
import { User } from 'src/model/user';
import { AuthService } from 'src/services/auth/auth.service';

@Component({
  selector: 'app-resi',
  templateUrl: './resi.component.html',
  styleUrls: ['./resi.component.sass']
})
export class ResiComponent implements OnInit {

  status : boolean = true;
  loggedUser: User | undefined;

  date = new Date()
  dateStr = this.date.toISOString().split('T')[0]


  constructor(private authService: AuthService) {
    this.authService.getLoggedUser().subscribe((user: User | undefined) => {
      this.loggedUser = user;
      console.log(this.loggedUser);
      
    });
   }

  ngOnInit(): void {
    
  }

}

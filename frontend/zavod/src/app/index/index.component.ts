import { Component, OnInit, Inject } from '@angular/core';
import { Router } from '@angular/router';
import {MatDialog, MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import { MatTab } from '@angular/material/tabs';
import { LoginComponent } from './login/login.component';
import { AuthService } from 'src/services/auth/auth.service';
import { User } from 'src/model/user';
import { ToastrService } from 'ngx-toastr';

export interface DialogData {
  animal: string;
  name: string;
}


@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.sass']
})
export class IndexComponent implements OnInit {

  animal: string = "";
  name: string = "";
  loggedUser: User | undefined;

  constructor(private router: Router, public dialog: MatDialog, private authService: AuthService, private toastr: ToastrService) { 
    this.authService.getLoggedUser().subscribe((user: User | undefined) => {
      console.log("USER SUBSCRIBED: ", user);
      
      this.loggedUser = user;
    })
  }

  ngOnInit(): void {
  }

  accessAutorska() {
    if(this.loggedUser) {
      if(this.loggedUser.uloga == "gradjanin") {
        this.router.navigate(['/autorska/novi-zahtev']);
      } else if(this.loggedUser.uloga == "sluzbenik") {
        this.router.navigate(['/autorska/sluzbenik/pretraga']);
      }
    } else {
      this.toastr.info("Potrebno je prvo prijaviti se na sistem.")
    }
  }

  accessZig() {
    if(this.loggedUser) {
      this.router.navigate(['/zig']);
    } else {
      this.toastr.info("Potrebno je prvo prijaviti se na sistem.")
    }
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(LoginComponent, {
      data: {name: this.name, animal: this.animal},
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.animal = result;
    });
  }

  logout() {
    this.authService.logout();
  }

}
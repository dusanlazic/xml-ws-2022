import { Component, OnInit, Inject } from '@angular/core';
import {MatDialog, MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import { MatTab } from '@angular/material/tabs';
import { DialogData } from '../index.component';
import { AuthService } from 'src/services/auth/auth.service';
import { User } from 'src/model/user';
import { IndividualConfig, ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent implements OnInit {

  hide = true;
  username: string = ""
  password: string = ""
  loggedUser: User | undefined;

  constructor(
    public dialogRef: MatDialogRef<LoginComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
    private authService: AuthService,
    private toastr: ToastrService
  ) {

  }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }


  login() {
    let that = this;
    let toastrConf : Partial<IndividualConfig> = {
      progressBar: true,
      timeOut: 3000
    }
    this.authService.login(this.username, this.password).subscribe(
      {
        next(value) {
            that.dialogRef.close();
            that.toastr.success("Dobrodosli!", "", toastrConf)
        },
        error(err) {
            that.toastr.error("Neispravni kredencijali!", "Greska", toastrConf)
        },
      }
    );
  }

}

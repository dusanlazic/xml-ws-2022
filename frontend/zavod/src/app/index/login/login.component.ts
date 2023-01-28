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
  passwordConfirm: string = ""
  name: string = ""
  lastname: string = ""
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

  register() {

    let zahtevZaRegistraciju = {
      email: this.username,
      lozinka: this.password,
      ime: this.name,
      prezime: this.lastname,
      uloga: 'sluzbenik'
    }

    if(!this.validate(zahtevZaRegistraciju)) {
      return;
    }

    let that = this;
    
    let toastrConf : Partial<IndividualConfig> = {
      progressBar: true,
      timeOut: 3000
    }

    this.authService.register(zahtevZaRegistraciju).subscribe({
      next(value) {
        that.dialogRef.close();
        that.toastr.success("Dobrodosli!", "", toastrConf)
      },
      error(err) {
        that.toastr.error("Postoji nalog sa datim podacima!")
      },
    })
  }

  validate(zahtevZaRegistraciju: any) {
    console.log(zahtevZaRegistraciju);
    
    if(zahtevZaRegistraciju.email == "" || zahtevZaRegistraciju.lozinka == "" || zahtevZaRegistraciju.ime == "" || zahtevZaRegistraciju.prezime == "") {
      this.toastr.error("Morate popuniti sva polja!")
      return false;
    }
    if(zahtevZaRegistraciju.lozinka != this.passwordConfirm) {
      this.toastr.error("Lozinke se ne poklapaju!")
      return false;
    }
    // validate email with regex
    const expression: RegExp = /^(?=.{1,254}$)(?=.{1,64}@)[-!#$%&'*+/0-9=?A-Z^_`a-z{|}~]+(\.[-!#$%&'*+/0-9=?A-Z^_`a-z{|}~]+)*@[A-Za-z0-9]([A-Za-z0-9-]{0,61}[A-Za-z0-9])?(\.[A-Za-z0-9]([A-Za-z0-9-]{0,61}[A-Za-z0-9])?)*$/;
    if(!expression.test(zahtevZaRegistraciju.email)) {
      this.toastr.error("Neispravan format email adrese!")
      return false;
    }
    return true;
  }

}

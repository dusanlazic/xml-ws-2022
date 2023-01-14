import { Component, OnInit, Inject } from '@angular/core';
import { Router } from '@angular/router';
import {MatDialog, MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import { MatTab } from '@angular/material/tabs';
import { LoginComponent } from './login/login.component';

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

  constructor(private router: Router, public dialog: MatDialog) { }

  ngOnInit(): void {
  }

  accessAutorska() {
    this.router.navigate(['/autorska']);
  }

  accessZig() {
    this.router.navigate(['/autorska']);
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

}
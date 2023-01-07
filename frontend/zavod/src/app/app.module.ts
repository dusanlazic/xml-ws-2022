import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IndexComponent } from './index/index.component';
import { SluzbenikComponent } from './sluzbenik/sluzbenik.component';
import { AutorskaComponent } from './autorska/autorska.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NoviZahtevComponent } from './autorska/novi-zahtev/novi-zahtev.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FormlyModule } from '@ngx-formly/core';
import { RouterModule } from '@angular/router';
import { FormlyFieldStepper } from './autorska/novi-zahtev/stepper.type';
import { FormlyMaterialModule } from '@ngx-formly/material';
import { MatStepperModule } from '@angular/material/stepper'
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import {MatIconModule} from '@angular/material/icon'
import { MatCardModule } from '@angular/material/card';

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    SluzbenikComponent,
    AutorskaComponent,
    NoviZahtevComponent,
    FormlyFieldStepper
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    FormlyMaterialModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MatStepperModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatCardModule,
    FormlyModule.forRoot({
      validationMessages: [{ name: 'required', message: 'This field is required' }],
      types: [{ name: 'stepper', component: FormlyFieldStepper, wrappers: [] }],
    }),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

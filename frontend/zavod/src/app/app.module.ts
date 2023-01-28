import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

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
import { MatIconModule } from '@angular/material/icon'
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list'
import { MatToolbarModule } from '@angular/material/toolbar'
import { MatButtonModule} from '@angular/material/button';
import { MojiZahteviComponent } from './autorska/moji-zahtevi/moji-zahtevi.component';
import { MojProfilComponent } from './autorska/moj-profil/moj-profil.component';
import { PretragaComponent } from './autorska/pretraga/pretraga.component';
import { MatDialogModule} from '@angular/material/dialog';
import { FormsModule } from '@angular/forms';
import { MatNativeDateModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatTabsModule } from '@angular/material/tabs';
import { LoginComponent } from './index/login/login.component';
import { MatChipsModule } from '@angular/material/chips'; 
import { MatInputModule } from '@angular/material/input';
import { FileValueAccessor } from './file-value-accessor';
import { MatSelectModule } from '@angular/material/select';
import { FormlyFieldFile } from './file-type.component';
import { PretragaMetapodaciComponent } from './autorska/pretraga/pretraga-metapodaci/pretraga-metapodaci.component';
import { PretragaRezultatiComponent } from './autorska/pretraga/pretraga-rezultati/pretraga-rezultati.component';
import { PretragaRezultatComponent } from './autorska/pretraga/pretraga-rezultati/pretraga-rezultat/pretraga-rezultat.component';
import { PretragaObicnaComponent } from './autorska/pretraga/pretraga-obicna/pretraga-obicna.component';
import { ToastrModule } from 'ngx-toastr';
import { MatRadioModule } from '@angular/material/radio'; 


@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    SluzbenikComponent,
    AutorskaComponent,
    NoviZahtevComponent,
    FormlyFieldStepper,
    MojiZahteviComponent,
    MojProfilComponent,
    PretragaComponent,
    LoginComponent,
    FileValueAccessor, FormlyFieldFile, PretragaMetapodaciComponent, PretragaRezultatiComponent, PretragaRezultatComponent, PretragaObicnaComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    HttpClientModule,
    MatSelectModule,
    MatInputModule,
    FormlyMaterialModule,
    MatChipsModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatNativeDateModule,
    FormsModule,
    MatTabsModule,
    MatStepperModule,
    MatDialogModule,
    MatGridListModule,
    MatRadioModule,
    MatSidenavModule,
    MatIconModule,
    MatButtonModule,
    MatListModule,
    MatToolbarModule,
    MatCardModule,
    FormlyModule.forRoot({
      validationMessages: [{ name: 'required', message: 'This field is required' }],
      types: [{ name: 'stepper', component: FormlyFieldStepper, wrappers: [] },
              { name: 'file', component: FormlyFieldFile, wrappers: [] }],
    }),
    ToastrModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

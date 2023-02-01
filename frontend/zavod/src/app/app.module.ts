import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IndexComponent } from './index/index.component';
import { SluzbenikComponent } from './sluzbenik/sluzbenik.component';
import { AutorskaComponent } from './autorska/autorska.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NoviZahtevAutorskaComponent } from './autorska/novi-zahtev/novi-zahtev-autorska.component';
import { NoviZahtevZigComponent } from './zig/novi-zahtev/novi-zahtev-zig.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FormlyModule } from '@ngx-formly/core';
import { RouterModule } from '@angular/router';
import { FormlyFieldAutorskaStepper } from './autorska/novi-zahtev/stepper.type';
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
import { PretragaComponent } from './shared/pretraga/pretraga.component';
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
import { PretragaMetapodaciComponent } from './shared/pretraga/pretraga-metapodaci/pretraga-metapodaci.component';
import { PretragaRezultatiComponent } from './shared/pretraga/pretraga-rezultati/pretraga-rezultati.component';
import { PretragaRezultatAutorskaComponent } from './shared/pretraga/pretraga-rezultati/pretraga-rezultat-autorska/pretraga-rezultat-autorska.component';
import { PretragaObicnaComponent } from './shared/pretraga/pretraga-obicna/pretraga-obicna.component';
import { ToastrModule } from 'ngx-toastr';
import { MatRadioModule } from '@angular/material/radio';
import { ZahteviComponent } from './sluzbenik/zahtevi/zahtevi.component';
import { IzvestajiComponent } from './sluzbenik/izvestaji/izvestaji.component';
import { ZigComponent } from './zig/zig.component';
import { PretragaRezultatZigComponent } from './shared/pretraga/pretraga-rezultati/pretraga-rezultat-zig/pretraga-rezultat-zig.component';
import { ZahtevZigComponent } from './zig/zahtev-zig/zahtev-zig.component';
import { ZahtevZigContentComponent } from './zig/zahtev-zig/zahtev-zig-content/zahtev-zig-content.component';
import { ResiComponent } from './shared/resi/resi.component';
import { ResenjeComponent } from './shared/resenje/resenje.component';
import { ZahtevAutorskaComponent } from './autorska/zahtev-autorska/zahtev-autorska.component';
import { ZahtevAutorksaContentComponent } from './autorska/zahtev-autorska/zahtev-autorksa-content/zahtev-autorksa-content.component'; 
import {MatCheckboxModule} from '@angular/material/checkbox'; 
import { FormlyFieldZigStepper } from './zig/novi-zahtev/stepper-zig.type';

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    SluzbenikComponent,
    AutorskaComponent,
    NoviZahtevAutorskaComponent,
    NoviZahtevZigComponent,
    FormlyFieldAutorskaStepper,
    FormlyFieldZigStepper,
    MojiZahteviComponent,
    PretragaComponent,
    LoginComponent,
    FileValueAccessor, 
    FormlyFieldFile,
    PretragaMetapodaciComponent,
    PretragaRezultatiComponent,
    PretragaRezultatAutorskaComponent, 
    PretragaObicnaComponent, 
    ZahteviComponent,
    IzvestajiComponent, 
    ZigComponent, 
    PretragaRezultatZigComponent, 
    ZahtevZigComponent, 
    ZahtevZigContentComponent, 
    ResiComponent, ResenjeComponent, ZahtevAutorskaComponent, ZahtevAutorksaContentComponent,
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
    MatCheckboxModule,
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
      types: [{ name: 'stepper-autorska', component: FormlyFieldAutorskaStepper, wrappers: [] },
              { name: 'stepper-zig', component: FormlyFieldZigStepper, wrappers: [] },
              { name: 'file', component: FormlyFieldFile, wrappers: [] }],
    }),
    ToastrModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { SluzbenikComponent } from './sluzbenik/sluzbenik.component';
import { AutorskaComponent } from './autorska/autorska.component';
import { PretragaComponent } from './autorska/pretraga/pretraga.component';
import { MojiZahteviComponent } from './autorska/moji-zahtevi/moji-zahtevi.component';
import { MojProfilComponent } from './autorska/moj-profil/moj-profil.component';
import { NoviZahtevComponent } from './autorska/novi-zahtev/novi-zahtev.component';
import { ZahteviComponent } from './sluzbenik/zahtevi/zahtevi.component';
import { IzvestajiComponent } from './sluzbenik/izvestaji/izvestaji.component';

const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'autorska', component: AutorskaComponent, children: [
    { path: 'pretraga', component: PretragaComponent },
    { path: 'moji-zahtevi', component: MojiZahteviComponent },
    { path: 'moj-profil', component: MojProfilComponent },
    { path: 'novi-zahtev', component: NoviZahtevComponent},
    
  ] },
  { path: 'autorska/sluzbenik', component: SluzbenikComponent, children: [
    { path: 'pretraga', component: PretragaComponent },
    { path: 'zahtevi', component: ZahteviComponent },
    { path: 'izvestaji', component: IzvestajiComponent }
  ]}
  
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { SluzbenikComponent } from './sluzbenik/sluzbenik.component';
import { AutorskaComponent } from './autorska/autorska.component';
import { PretragaComponent } from './autorska/pretraga/pretraga.component';
import { MojiZahteviComponent } from './autorska/moji-zahtevi/moji-zahtevi.component';
import { MojProfilComponent } from './autorska/moj-profil/moj-profil.component';
import { NoviZahtevComponent } from './autorska/novi-zahtev/novi-zahtev.component';

const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'sluzbenik', component: SluzbenikComponent },
  { path: 'autorska', component: AutorskaComponent, children: [
    { path: 'pretraga', component: PretragaComponent },
    { path: 'moji-zahtevi', component: MojiZahteviComponent },
    { path: 'moj-profil', component: MojProfilComponent },
    { path: 'novi-zahtev', component: NoviZahtevComponent}
  ] },
  
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

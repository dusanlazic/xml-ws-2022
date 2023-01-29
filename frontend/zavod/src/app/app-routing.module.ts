import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { SluzbenikComponent } from './sluzbenik/sluzbenik.component';
import { AutorskaComponent } from './autorska/autorska.component';
import { PretragaComponent } from './shared/pretraga/pretraga.component';
import { MojiZahteviComponent } from './autorska/moji-zahtevi/moji-zahtevi.component';
import { MojProfilComponent } from './shared/moj-profil/moj-profil.component';
import { NoviZahtevAutorskaComponent } from './autorska/novi-zahtev/novi-zahtev-autorska.component';
import { ZahteviComponent } from './sluzbenik/zahtevi/zahtevi.component';
import { IzvestajiComponent } from './sluzbenik/izvestaji/izvestaji.component';
import { ZigComponent } from './zig/zig.component';
import { ZahtevZigComponent } from './zig/zahtev-zig/zahtev-zig.component';

const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'autorska', component: AutorskaComponent, children: [
      { path: 'pretraga', component: PretragaComponent },
      { path: 'moji-zahtevi', component: MojiZahteviComponent },
      { path: 'moj-profil', component: MojProfilComponent },
      { path: 'novi-zahtev', component: NoviZahtevAutorskaComponent},
    ]
  },
  { path: 'zig', component: ZigComponent, children: [
      { path: 'pretraga', component: PretragaComponent },
      { path: 'moji-zahtevi', component: MojiZahteviComponent },
      { path: 'moj-profil', component: MojProfilComponent },
      { path: 'novi-zahtev', component: NoviZahtevAutorskaComponent },
      { path: 'zahtev/:broj_prijave', component: ZahtevZigComponent }
    ]
  },
  { path: 'autorska/sluzbenik', component: SluzbenikComponent, children: [
      { path: 'pretraga', component: PretragaComponent },
      { path: 'zahtevi', component: ZahteviComponent },
      { path: 'izvestaji', component: IzvestajiComponent }
    ]
  },


  
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { SluzbenikComponent } from './sluzbenik/sluzbenik.component';
import { AutorskaComponent } from './autorska/autorska.component';

const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'sluzbenik', component: SluzbenikComponent },
  { path: 'autorska', component: AutorskaComponent },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

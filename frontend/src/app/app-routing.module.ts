import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {HomeComponent} from "./home/home.component";
import {AnimalsComponent} from "./animals/animals.component";
import {VisitsComponent} from "./visits/visits.component";
import {AuthGuard} from "./_guard/auth.guard";
import {AnimalComponent} from "./animal/animal.component";
import {VisitComponent} from "./visit/visit.component";
import {VisitAddComponent} from "./visit-add/visit-add.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'animals', component: AnimalsComponent, canActivate: [AuthGuard] },
  { path: 'animal/:id', component: AnimalComponent, canActivate: [AuthGuard] },
  { path: 'visits', component: VisitsComponent, canActivate: [AuthGuard] },
  { path: 'visits/archive', component: VisitsComponent, canActivate: [AuthGuard] },
  { path: 'visits/add', component: VisitAddComponent, canActivate: [AuthGuard] },
  { path: 'visit/:id', component: VisitComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

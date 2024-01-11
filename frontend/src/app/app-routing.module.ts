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
import {AnimalSurgeryComponent} from "./animal-surgery/animal-surgery.component";
import {AnimalSurgeriesComponent} from "./animal-surgeries/animal-surgeries.component";
import {AnimalSurgeryAddComponent} from "./animal-surgery-add/animal-surgery-add.component";
import {SurgeriesComponent} from "./surgeries/surgeries.component";
import {SurgeryComponent} from "./surgery/surgery.component";
import {ShiftsComponent} from "./shifts/shifts.component";
import {ShiftComponent} from "./shift/shift.component";
import {RegisterComponent} from "./register/register.component";
import {AssortmentsComponent} from "./assortments/assortments.component";
import {AssortmentComponent} from "./assortment/assortment.component";
import {AssortmentAddComponent} from "./assortment-add/assortment-add.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'animals', component: AnimalsComponent, canActivate: [AuthGuard] },
  { path: 'animal/:id', component: AnimalComponent, canActivate: [AuthGuard] },
  { path: 'visits', component: VisitsComponent, canActivate: [AuthGuard] },
  { path: 'visits/archive', component: VisitsComponent, canActivate: [AuthGuard] },
  { path: 'visits/add', component: VisitAddComponent, canActivate: [AuthGuard] },
  { path: 'visit/:id', component: VisitComponent, canActivate: [AuthGuard] },
  { path: 'animalSurgery/:id', component: AnimalSurgeryComponent, canActivate: [AuthGuard] },
  { path: 'animalSurgeries', component: AnimalSurgeriesComponent, canActivate: [AuthGuard] },
  { path: 'animalSurgery/add/:id', component: AnimalSurgeryAddComponent, canActivate: [AuthGuard] },
  { path: 'surgeries', component: SurgeriesComponent, canActivate: [AuthGuard] },
  { path: 'surgery/:id', component: SurgeryComponent, canActivate: [AuthGuard] },
  { path: 'shifts', component: ShiftsComponent, canActivate: [AuthGuard] },
  { path: 'shift/:id', component: ShiftComponent, canActivate: [AuthGuard] },
  { path: 'assortments', component: AssortmentsComponent, canActivate: [AuthGuard] },
  { path: 'assortment/:id', component: AssortmentComponent, canActivate: [AuthGuard] },
  { path: 'assortmentAdd', component: AssortmentAddComponent, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

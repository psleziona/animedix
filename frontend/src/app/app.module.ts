import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {RouterOutlet} from "@angular/router";
import {AppRoutingModule} from "./app-routing.module";
import { LoginComponent } from './login/login.component';
import { MenuComponent } from './menu/menu.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from "@angular/forms";
import {AuthInterceptor} from "./_interceptor/auth.interceptor";
import {AuthGuard} from "./_guard/auth.guard";
import { AnimalsComponent } from './animals/animals.component';
import { VisitsComponent } from './visits/visits.component';
import { AnimalComponent } from './animal/animal.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import { EmployeesComponent } from './employees/employees.component';
import { EmployeeComponent } from './employee/employee.component';
import { PaginationComponent } from './pagination/pagination.component';
import { VisitComponent } from './visit/visit.component';
import { VisitAddComponent } from './visit-add/visit-add.component';
import { EmployeeAddComponent } from './employee-add/employee-add.component';
import { AnimalSurgeryComponent } from './animal-surgery/animal-surgery.component';
import { AnimalSurgeryAddComponent } from './animal-surgery-add/animal-surgery-add.component';
import { SurgeriesComponent } from './surgeries/surgeries.component';
import { SurgeryComponent } from './surgery/surgery.component';
import { AssortmentsComponent } from './assortments/assortments.component';
import { AssortmentComponent } from './assortment/assortment.component';
import { AssortmentAddComponent } from './assortment-add/assortment-add.component';
import { ShiftsComponent } from './shifts/shifts.component';
import { AnimalSurgeriesComponent } from './animal-surgeries/animal-surgeries.component';
import { RegisterComponent } from './register/register.component';
import { ClientComponent } from './client/client.component';
import { ShiftGeneratorComponent } from './shift-generator/shift-generator.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MenuComponent,
    AnimalsComponent,
    VisitsComponent,
    AnimalComponent,
    EmployeesComponent,
    EmployeeComponent,
    PaginationComponent,
    VisitComponent,
    VisitAddComponent,
    EmployeeAddComponent,
    AnimalSurgeryComponent,
    AnimalSurgeryAddComponent,
    SurgeriesComponent,
    SurgeryComponent,
    AssortmentsComponent,
    AssortmentComponent,
    AssortmentAddComponent,
    ShiftsComponent,
    AnimalSurgeriesComponent,
    RegisterComponent,
    ClientComponent,
    ShiftGeneratorComponent
  ],
  imports: [
    BrowserModule,
    RouterOutlet,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatFormFieldModule,
    MatInputModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

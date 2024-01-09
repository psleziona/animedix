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
import { HomeComponent } from './home/home.component';
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

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MenuComponent,
    AnimalsComponent,
    VisitsComponent,
    HomeComponent,
    AnimalComponent,
    EmployeesComponent,
    EmployeeComponent,
    PaginationComponent,
    VisitComponent,
    VisitAddComponent
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

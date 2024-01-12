import { Component } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {EmployeeService} from "../_service/employee.service";
import {Employee} from "../_model/employee";
import {Router} from "@angular/router";

@Component({
  selector: 'app-employee-add',
  templateUrl: './employee-add.component.html',
  styleUrls: ['./employee-add.component.css']
})
export class EmployeeAddComponent {
  employeeAddForm = new FormGroup({
    forename: new FormControl(),
    surname: new FormControl(),
    phone: new FormControl(),
    email: new FormControl(),
    password: new FormControl(),
    street: new FormControl(),
    houseNumber: new FormControl(),
    zipcode: new FormControl(),
    city: new FormControl()
  })

  constructor(private employeeService: EmployeeService, private router: Router) {}

  addEmployee() {
    const e : Employee = {
      forename: this.employeeAddForm.value.forename ?? '',
      surname: this.employeeAddForm.value.surname ?? '',
      phone: this.employeeAddForm.value.phone ?? '',
      email: this.employeeAddForm.value.email ?? '',
      password: this.employeeAddForm.value.password ?? '',
      street: this.employeeAddForm.value.street ?? '',
      houseNumber: this.employeeAddForm.value.houseNumber ?? '',
      zipcode: this.employeeAddForm.value.zipcode ?? '',
      city: this.employeeAddForm.value.city ?? ''
    }
    this.employeeService.addEmployee(e).subscribe(v => this.router.navigateByUrl("/employees"));
  }
}

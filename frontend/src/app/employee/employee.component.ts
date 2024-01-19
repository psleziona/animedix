import { Component } from '@angular/core';
import {Employee} from "../_model/employee";
import {EmployeeService} from "../_service/employee.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent {
  employee? : Employee;

  constructor(private employeeService : EmployeeService, private route : ActivatedRoute) {
    this.employeeService.getEmployee(this.route.snapshot.params['id']).subscribe(res => this.employee = res);
  }
}

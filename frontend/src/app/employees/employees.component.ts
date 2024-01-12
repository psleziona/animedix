import { Component } from '@angular/core';
import {Observable, Subject} from "rxjs";
import {Employee} from "../_model/employee";
import {EmployeeService} from "../_service/employee.service";

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent {
  employees$: Subject<Employee[]> = new Subject<Employee[]>();

  constructor(private employeeService: EmployeeService) {
    employeeService.getEmployees().subscribe(res => {
      // @ts-ignore
      this.employees$.next(res['content'])
    });
  }
}

import { Component } from '@angular/core';
import {Observable, Subject} from "rxjs";
import {Employee} from "../_model/employee";
import {EmployeeService} from "../_service/employee.service";
import {StorageService} from "../_service/storage.service";

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent {
  employees$: Subject<Employee[]> = new Subject<Employee[]>();

  constructor(private employeeService: EmployeeService,
              private storageService: StorageService) {
    employeeService.getEmployees().subscribe(res => {
      // @ts-ignore
      this.employees$.next(res['content'])
    });
  }
}

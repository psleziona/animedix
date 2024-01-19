import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Employee} from "../_model/employee";

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private employeeUrl = 'http://localhost:8080/api/employees'
  constructor(private http: HttpClient) { }

  getEmployee(id: number) {
    return this.http.get<Employee>(this.employeeUrl + "/" + id);
  }

  getEmployees() {
    return this.http.get(this.employeeUrl);
  }

  addEmployee(employee: Employee) {
    return this.http.post(this.employeeUrl, employee);
  }

}

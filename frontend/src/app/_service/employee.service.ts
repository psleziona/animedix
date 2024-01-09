import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private employeeUrl = 'http://localhost:8080/api/employees'
  constructor(private http: HttpClient) { }

  getDoctor() {

  }

}

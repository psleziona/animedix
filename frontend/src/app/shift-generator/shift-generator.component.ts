import { Component } from '@angular/core';
import {EmployeeService} from "../_service/employee.service";
import {ShiftService} from "../_service/shift.service";
import {Employee} from "../_model/employee";
import {FormControl} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-shift-generator',
  templateUrl: './shift-generator.component.html',
  styleUrls: ['./shift-generator.component.css']
})
export class ShiftGeneratorComponent {
  employees? : Employee[];
  date;
  constructor(private employeeService:EmployeeService,
              private shiftService: ShiftService,
              private router: Router) {
    this.employeeService.getEmployees().subscribe(res => {
      // @ts-ignore
      this.employees = res['content']
    });

    const today = new Date();
    let currentY = today.getFullYear();
    let nextM = today.getMonth() + 2;
    if(nextM === 13) {
      currentY++;
      nextM = 1;
    }
    this.date = new FormControl(`${currentY}-${nextM < 10 ? '0' : ''}${nextM}`);
  }

  generateShifts() {
    const s = {
      month: this.date.value,
    }
    // @ts-ignore
    for(let e of this.employees) {
      // @ts-ignore
      s[e.id] = document.querySelector(`#employee${e.id}`).value;
    }
    this.shiftService.generateShifts(s).subscribe(r => this.router.navigateByUrl("/shifts"));
  }
}

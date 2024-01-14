import { Component } from '@angular/core';
import {ShiftService} from "../_service/shift.service";
import {ActivatedRoute, Router} from "@angular/router";
import {filter, Observable} from "rxjs";
import {Shift} from "../_model/shift";
import {FormControl} from "@angular/forms";
import {StorageService} from "../_service/storage.service";
import * as XLSX from 'xlsx';
import {Employee} from "../_model/employee";

@Component({
  selector: 'app-shifts',
  templateUrl: './shifts.component.html',
  styleUrls: ['./shifts.component.css']
})
export class ShiftsComponent {
  selectedDate= new FormControl();
  shifts? : Shift[];
  showedShifts? : Shift[];
  availableUsers?: any[];
  role = '';
  maxDayShiftEmployee = 0;
  userSelect = new FormControl();
  constructor(private shiftService: ShiftService,
              private route: ActivatedRoute,
              private storageService: StorageService,
              private router : Router) {
    this.role = storageService.getRole();
  }

  ngOnInit() {
    this.route.url.subscribe(segments => {
      if(segments.length == 1) {
        const date = new Date();
        const month = (date.getMonth() + 1) < 10 ? `0${date.getMonth() + 1}` : date.getMonth() + 1;
        this.shiftService.getMonthShifts(`${date.getFullYear()}-${month}-01`)
          .subscribe(res => this.extractData(res));
      } else if(segments.length > 1) {
        const type = segments[1].path;
        if(type == 'month')
          this.shiftService.getMonthShifts(segments[2].path + "-01")
            .subscribe(res => this.extractData(res));
        else if(type == 'day')
          this.shiftService.getDayShift(segments[2].path)
            .subscribe(res => this.extractData(res));
      }

    });

  }

  onMonthChange() {
    this.router.navigateByUrl("shifts/month/" + this.selectedDate.value);
  }

  onDayChange() {
    this.router.navigateByUrl("shifts/day/" + this.selectedDate.value);
  }

  getExcel() {
    let data = document.getElementById('shifts');
    const ws:XLSX.WorkSheet = XLSX.utils.table_to_sheet(data);

    const wb:XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
    XLSX.writeFile(wb, 'Shifts.xlsx');
  }

  extractData(res: Shift[]) {
    this.shifts = res;
    this.showedShifts = res;
    this.availableUsers = this.shifts?.flatMap(s => s.employees ?? []);
    this.availableUsers = Array.from(new Set(this.availableUsers?.map(o => JSON.stringify(o))))
      .map(s => JSON.parse(s));
  }

  onUserSelected() {
    const id = this.userSelect.value;
    if(id == '')
      this.showedShifts = this.shifts;
    else
      this.showedShifts = this.availableUsers?.filter(u => u.id == id)
        .flatMap(u => u['shifts']).sort((a,b) => a.shiftStart < b.shiftStart ? -1 : 1);
  }

}

import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Shift} from "../_model/shift";

@Injectable({
  providedIn: 'root'
})
export class ShiftService {
  url = 'http://localhost:8080/api/shifts';
  constructor(private http: HttpClient) { }

  getMonthShifts(month : string) {
    return this.http.get<Shift[]>(this.url + "/month/" + month);
  }

  getDayShift(day : string) {
    return this.http.get<Shift[]>(this.url + "/date/" + day);
  }

  addShift(shift : any) {
    return this.http.post(this.url, shift);
  }

  generateShifts(shift : object) {
    return this.http.post(this.url + "/generate", shift);
  }
}

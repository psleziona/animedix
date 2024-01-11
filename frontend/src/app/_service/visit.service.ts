import { Injectable } from '@angular/core';
import {Visit} from "../_model/visit";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class VisitService {
  private visitUrl = 'http://localhost:8080/api/visits';
  constructor(private http: HttpClient) { }

  getUpcomingVisits() {
    return this.http.get(this.visitUrl + "/upcoming");
  }

  getArchiveVisits() {
    const date = new Date();
    const currentTime = `${date.getFullYear()}-${this.addLeadingZeros(date.getMonth() + 1)}-${this.addLeadingZeros(date.getDate())}T${this.addLeadingZeros(date.getHours())}:${this.addLeadingZeros(date.getMinutes())}:${this.addLeadingZeros(date.getSeconds())}`;
    return this.http.get(this.visitUrl + `/period/1970-01-01T00:00:00/${currentTime}`);
  }

  getVisit(idVisit: number) {
    return this.http.get<Visit>(this.visitUrl + "/" + idVisit);
  }

  addVisit(visit: Visit) {
    return this.http.post(this.visitUrl, visit);
  }

  rateVisit(idVisit:number, rate: number) {
    return this.http.post(this.visitUrl + `/rate/${idVisit}/${rate}`, {});
  }

  addDoctorComment(visit: Visit) {
    return this.http.put(this.visitUrl + `/${visit.idVisit}`, visit);
  }

  addLeadingZeros(num:number) {
    return num < 10 ? `0${num}` : num;
  }

}

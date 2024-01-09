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
    const currentTime = `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}T${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`
    return this.http.get(this.visitUrl + `/period/1970-01-01T00:00:00/${currentTime}`);
  }

  getDoctorVisits(idDoctor: number) {
    return this.http.get(this.visitUrl + "/doctor/" + idDoctor);
  }

  addVisit(visit: Visit) {
    return this.http.post<Visit>(this.visitUrl, visit);
  }

  rateVisit(idVisit:number, rate: number) {
    return this.http.post(this.visitUrl + `/rate/${idVisit}/${rate}`, {});
  }

  getAvailableDeadlines(from: string, to: string) {

  }

  getAvailableDoctors(from: string, to: string) {

  }

  getDoctorAvailableDeadlines(idDoctor: number) {

  }
}

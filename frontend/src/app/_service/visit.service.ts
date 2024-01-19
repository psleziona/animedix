import { Injectable } from '@angular/core';
import {Visit} from "../_model/visit";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class VisitService {
  private visitUrl = 'http://localhost:8080/api/visits';
  constructor(private http: HttpClient) { }

  getVisits(archive:boolean) {
    return archive ? this.getArchiveVisits() : this.getUpcomingVisits();
  }

  getUpcomingVisits() {
    return this.http.get(this.visitUrl + "/upcoming");
  }

  getArchiveVisits() {
    return this.http.get(this.visitUrl + `/archive`);
  }

  getNextVisit() {
    return this.http.get<Visit>(this.visitUrl + "/current");
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

}

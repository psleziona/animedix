import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {DoctorTerm} from "../_model/doctor-term";

@Injectable({
  providedIn: 'root'
})
export class DoctorTermService {
  url = 'http://localhost:8080/api/term';
  constructor(private http: HttpClient) { }

  getAvailableTerms(dayFrom: string, dayTo:string, hourFrom:string,hourTo:string) {
    return this.http.get<DoctorTerm[]>(this.url + `/${dayFrom}/${dayTo}/${hourFrom}/${hourTo}`);
  }

  getDoctorAvailableTerms(dayFrom: string, dayTo:string, hourFrom:string,hourTo:string,idDoctor: number) {
    return this.http.get<DoctorTerm>(this.url + `doctor/${idDoctor}/${dayFrom}/${dayTo}/${hourFrom}/${hourTo}`);
  }
}

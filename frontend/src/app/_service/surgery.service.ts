import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Surgery} from "../_model/surgery";

@Injectable({
  providedIn: 'root'
})
export class SurgeryService {
  url = 'http://localhost:8080/api/surgeries'
  constructor(private http: HttpClient) { }

  getSurgeries() {
    return this.http.get<Surgery[]>(this.url);
  }

  addSurgery(surgery: any) {
    return this.http.post(this.url, surgery);
  }
}

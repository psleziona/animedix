import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Assortment} from "../_model/assortment";

@Injectable({
  providedIn: 'root'
})
export class AssortmentService {
  url = 'http://localhost:8080/api/assortments'
  constructor(private http: HttpClient) { }

  getAssortment() {
    return this.http.get<Assortment[]>(this.url);
  }

  addAssortment(assortment: any) {
    return this.http.post(this.url, assortment);
  }

}

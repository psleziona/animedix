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

  getAssortmentById(id: number) {
    return this.http.get<Assortment>(this.url + "/" + id);
  }

  getAssortmentCritical() {
    return this.http.get<Assortment[]>(this.url + "/critical");
  }

  addAssortment(assortment: any) {
    return this.http.post(this.url, assortment);
  }

  fillAssortment(idAssortment: number, value : number) {
    return this.http.get(this.url + `/fill/${idAssortment}/${value}`);
  }

}

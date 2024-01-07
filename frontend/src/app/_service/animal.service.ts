import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Animal} from "../_model/animal";

@Injectable({
  providedIn: 'root'
})
export class AnimalService {

  private animalsUrl = 'http://localhost:8080/api/animals';
  constructor(private http: HttpClient) { }

  getAnimals(pageNumber: number, pageSize: number) {
    let params = new HttpParams();
    params = params.append('page', pageNumber.toString());
    params = params.append('size', pageSize.toString());
    return this.http.get(this.animalsUrl, {params});
  }

  getAnimal(id: number) {
    return this.http.get<Animal>(this.animalsUrl + `/${id}`);
  }

  updateAnimal(animal: Animal) {

  }

  addAnimal(animal: Animal) {

  }

  deleteAnimal(id: number) {

  }
}

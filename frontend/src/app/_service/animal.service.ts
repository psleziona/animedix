import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Animal} from "../_model/animal";

@Injectable({
  providedIn: 'root'
})
export class AnimalService {

  constructor(private http: HttpClient) { }

  getAnimals() {

  }

  getAnimal(id: number) {

  }

  updateAnimal(animal: Animal) {

  }

  addAnimal(animal: Animal) {

  }

  deleteAnimal(id: number) {

  }
}

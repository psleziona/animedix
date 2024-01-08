import { Component } from '@angular/core';
import {AnimalService} from "../_service/animal.service";
import {Animal} from "../_model/animal";
import {StorageService} from "../_service/storage.service";
import {FormControl, FormGroup} from "@angular/forms";
import {MatDatepickerModule} from '@angular/material/datepicker';

@Component({
  selector: 'app-animals',
  templateUrl: './animals.component.html',
  styleUrls: ['./animals.component.css']
})
export class AnimalsComponent {
  animals : Animal[] = [];
  animalForm = new FormGroup({
    name: new FormControl(''),
    species: new FormControl(''),
    breed: new FormControl(''),
    dateOfBirth: new FormControl('')
  });
  role = '';

  constructor(private animalService: AnimalService, private storageService: StorageService) {
    this.role = storageService.getRole();
  }
  ngOnInit() {
    this.animalService.getAnimals(0,10).subscribe({
      next: animal => {
        // @ts-ignore
        this.animals = animal['content'];
      }
    });
  }

  addAnimal() {
    const body : Animal = {
      species: this.animalForm.value.species ?? '',
      breed: this.animalForm.value.breed ?? '',
      name: this.animalForm.value.name ?? '',
      dateOfBirth: this.animalForm.value.dateOfBirth ?? ''
    }
    this.animalService.addAnimal(body).subscribe();
  }
}

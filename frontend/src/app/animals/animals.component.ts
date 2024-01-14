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

  //pagination
  pageNumber = 0;
  pageSize = 10;
  first = 0;
  last = 0;
  totalPages = 1;


  constructor(private animalService: AnimalService, private storageService: StorageService) {
    this.role = storageService.getRole();
  }
  ngOnInit() {
    this.animalService.getAnimals(0,10).subscribe({
      next: response => {
        // @ts-ignore
        this.animals = response['content'];
        //@ts-ignore
        this.totalPages = response['totalPages'];
        this.last = this.totalPages - 1;
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
    this.animalService.addAnimal(body).subscribe(value => window.location.reload());
  }
}

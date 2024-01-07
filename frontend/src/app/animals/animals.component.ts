import { Component } from '@angular/core';
import {AnimalService} from "../_service/animal.service";
import {Animal} from "../_model/animal";
import {StorageService} from "../_service/storage.service";

@Component({
  selector: 'app-animals',
  templateUrl: './animals.component.html',
  styleUrls: ['./animals.component.css']
})
export class AnimalsComponent {
  animals : Animal[] = [];
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
    })
  }
}

import {Component, inject} from '@angular/core';
import {AnimalService} from "../_service/animal.service";
import {StorageService} from "../_service/storage.service";
import {ActivatedRoute} from "@angular/router";
import {map, Observable} from "rxjs";
import {Animal} from "../_model/animal";

@Component({
  selector: 'app-animal',
  templateUrl: './animal.component.html',
  styleUrls: ['./animal.component.css']
})
export class AnimalComponent {
  route: ActivatedRoute = inject(ActivatedRoute);
  // @ts-ignore
  animal$: Observable<Animal>;
  constructor(private animalService: AnimalService) { }

  ngOnInit() {
    const idAnimal = Number(this.route.snapshot.params['id']);
    this.animal$ = this.animalService.getAnimal(idAnimal);
  }
}

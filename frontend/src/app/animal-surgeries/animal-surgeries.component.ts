import { Component } from '@angular/core';
import {Subject} from "rxjs";
import {AnimalSurgery} from "../_model/animal-surgery";

@Component({
  selector: 'app-animal-surgeries',
  templateUrl: './animal-surgeries.component.html',
  styleUrls: ['./animal-surgeries.component.css']
})
export class AnimalSurgeriesComponent {
  animalSurgeries$ = new Subject<AnimalSurgery>();
}

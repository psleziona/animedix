import { Component } from '@angular/core';
import {DoctorTermService} from "../_service/doctor-term.service";
import {AnimalSurgeryService} from "../_service/animal-surgery.service";
import {Route} from "@angular/router";

@Component({
  selector: 'app-animal-surgery-add',
  templateUrl: './animal-surgery-add.component.html',
  styleUrls: ['./animal-surgery-add.component.css']
})
export class AnimalSurgeryAddComponent {
  constructor(private doctorTermService: DoctorTermService,
              private animalSurgeryService: AnimalSurgeryService,
              private route: Route) { }

}

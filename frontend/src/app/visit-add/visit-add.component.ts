import { Component } from '@angular/core';
import {AnimalService} from "../_service/animal.service";
import {EmployeeService} from "../_service/employee.service";
import {Animal} from "../_model/animal";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-visit-add',
  templateUrl: './visit-add.component.html',
  styleUrls: ['./visit-add.component.css']
})
export class VisitAddComponent {
  animals?: Animal[];
  visitForm = new FormGroup({
    animal: new FormControl(''),
    date: new FormControl(''),
    ownerComments: new FormControl(''),
    doctor: new FormControl('')
  })


  constructor(private animalService: AnimalService, private employeeService: EmployeeService) { }

  ngOnInit() {
    this.animalService.getAnimals(0, 20).subscribe(next => {
      // @ts-ignore
      this.animals = next.content;
    })
  }

  addVisit() {

  }
}

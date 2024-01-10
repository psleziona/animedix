import { Component } from '@angular/core';
import {AnimalService} from "../_service/animal.service";
import {EmployeeService} from "../_service/employee.service";
import {Animal} from "../_model/animal";
import {FormControl, FormGroup} from "@angular/forms";
import {Employee} from "../_model/employee";

@Component({
  selector: 'app-visit-add',
  templateUrl: './visit-add.component.html',
  styleUrls: ['./visit-add.component.css']
})
export class VisitAddComponent {
  animals?: Animal[];
  availableDoctors?: Employee[];
  availableTerms?: string[];


  visitForm = new FormGroup({
    animal: new FormControl(''),
    date: new FormControl(''),
    ownerComments: new FormControl(''),
    doctor: new FormControl('')
  });

  availableTermForm = new FormGroup({
    dayFrom: new FormControl(''),
    dayTo: new FormControl(''),
    hourFrom: new FormControl('from'),
    hourTo: new FormControl('to')
  })

  visitHours = ['08:00','08:30','09:00','09:30','10:00','10:30','11:00','11:30','12:00','12:30','13:00','13:30','14:00','14:30','15:00','15:30'];

  constructor(private animalService: AnimalService, private employeeService: EmployeeService) { }

  ngOnInit() {
    this.animalService.getAnimals(0, 20).subscribe(next => {
      // @ts-ignore
      this.animals = next.content;
    });
  }

  addVisit() {

  }

  onSetVisitTerm() {
    console.log(this.availableTermForm);
  }
}

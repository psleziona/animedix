import { Component } from '@angular/core';
import {AnimalService} from "../_service/animal.service";
import {EmployeeService} from "../_service/employee.service";
import {Animal} from "../_model/animal";
import {FormControl, FormGroup} from "@angular/forms";
import {Employee} from "../_model/employee";
import {DoctorTerm} from "../_model/doctor-term";
import {DoctorTermService} from "../_service/doctor-term.service";
import {Observable, Subject} from "rxjs";
import {Visit} from "../_model/visit";
import {VisitService} from "../_service/visit.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-visit-add',
  templateUrl: './visit-add.component.html',
  styleUrls: ['./visit-add.component.css']
})
export class VisitAddComponent {
  animals?: Animal[];
  availableTerms?: DoctorTerm[];
  terms : Subject<string[]>;


  visitForm = new FormGroup({
    animal: new FormControl(''),
    date: new FormControl(''),
    ownerComments: new FormControl(''),
    doctor: new FormControl('')
  });

  availableTermForm = new FormGroup({
    dayFrom: new FormControl(''),
    dayTo: new FormControl(''),
    hourFrom: new FormControl(''),
    hourTo: new FormControl('')
  })

  visitHours = ['08:00','08:30','09:00','09:30','10:00','10:30','11:00','11:30','12:00','12:30','13:00','13:30','14:00','14:30','15:00','15:30'];

  constructor(private animalService: AnimalService,
              private doctorTermService: DoctorTermService,
              private visitService: VisitService,
              private router: Router) {
    this.terms = new Subject<string[]>();
  }

  ngOnInit() {
    this.animalService.getAnimals(0, 20).subscribe(next => {
      // @ts-ignore
      this.animals = next.content;
    });
  }

  addVisit() {
    const idAnimal = this.visitForm.value.animal ?? '';
    const ownerComments = this.visitForm.value.ownerComments ?? '';
    const date = this.visitForm.value.date ?? '';
    const idDoctor = this.visitForm.value.doctor ?? '';
    // @ts-ignore
    const animal : Animal = this.animals?.filter(a => a.idAnimal == Number(idAnimal))[0];
    // @ts-ignore
    const doctor : Employee = this.availableTerms?.filter(dt => dt.doctor.id == Number(idDoctor))
      .map(d => d.doctor)[0];

    const visit : Visit = {
      date: date,
      ownerComments: ownerComments,
      animal: animal,
      doctor: doctor
    }

    this.visitService.addVisit(visit).subscribe(
      value => this.router.navigateByUrl('/visits')
    );
  }

  onSetVisitTerm() {
    const dayFrom = this.availableTermForm.value.dayFrom ?? '';
    const dayTo = this.availableTermForm.value.dayTo ?? '';
    const hourFrom = this.availableTermForm.value.hourFrom ?? '';
    const hourTo = this.availableTermForm.value.hourTo ?? '';
    this.doctorTermService.getAvailableTerms(dayFrom,dayTo,hourFrom,hourTo).subscribe(
      content => this.availableTerms = content
    )
  }

  getDoctorVisitTerm() {
    const doctor = this.visitForm.value.doctor ?? '';
    this.availableTerms?.filter(x => x.doctor.id == Number(doctor))
      .map(x => {
        this.terms.next(x.availableTerms);
      })
  }
}

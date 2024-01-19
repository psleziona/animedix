import { Component } from '@angular/core';
import {DoctorTermService} from "../_service/doctor-term.service";
import {AnimalSurgeryService} from "../_service/animal-surgery.service";
import {ActivatedRoute, Route, Router} from "@angular/router";
import {FormControl, FormGroup} from "@angular/forms";
import {Subject} from "rxjs";
import {Animal} from "../_model/animal";
import {AnimalService} from "../_service/animal.service";
import {DoctorTerm} from "../_model/doctor-term";
import {Surgery} from "../_model/surgery";
import {SurgeryService} from "../_service/surgery.service";
import {AnimalSurgery} from "../_model/animal-surgery";
import {StorageService} from "../_service/storage.service";

@Component({
  selector: 'app-animal-surgery-add',
  templateUrl: './animal-surgery-add.component.html',
  styleUrls: ['./animal-surgery-add.component.css']
})
export class AnimalSurgeryAddComponent {
  terms : Subject<string[]>;
  animal? : Animal;
  availableTermForm = new FormGroup({
    dayFrom: new FormControl(''),
    dayTo: new FormControl('')
  });
  surgeries?: Surgery[];
  selectedSurgery = new FormControl('');
  surgeryDate = new FormControl('');

  constructor(private doctorTermService: DoctorTermService,
              private animalSurgeryService: AnimalSurgeryService,
              private animalService: AnimalService,
              private surgeryService: SurgeryService,
              private storageService: StorageService,
              private route : ActivatedRoute,
              private router: Router) {
    this.terms = new Subject<string[]>();
  }

  ngOnInit() {
    const idAnimal = Number(this.route.snapshot.params['id']);
    this.animalService.getAnimal(idAnimal).subscribe(animal => this.animal = animal);
    this.surgeryService.getSurgeries().subscribe(res => this.surgeries = res)
  }

  getAvailableTerms() {
    const dayFrom = this.availableTermForm.value.dayFrom ?? '';
    const dayTo = this.availableTermForm.value.dayTo ?? '';
    this.doctorTermService.getDoctorAvailableTerms(dayFrom, dayTo).subscribe(
      response => this.terms.next(response.availableTerms)
    )
  }

  setSurgery() {
    const date = this.surgeryDate.value ?? '';
    const animalSurgery = {
      idAnimal: this.animal?.idAnimal,
      idDoctor: this.storageService.getUser().id,
      date: date,
      idSurgery: Number(this.selectedSurgery.value)
    }
    this.animalSurgeryService.setAnimalSurgery(animalSurgery).subscribe(
      next => this.router.navigateByUrl('/animalSurgeries')
    );
  }

}

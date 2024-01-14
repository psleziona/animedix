import { Component } from '@angular/core';
import {Observable} from "rxjs";
import {Surgery} from "../_model/surgery";
import {SurgeryService} from "../_service/surgery.service";
import {FormControl, FormGroup} from "@angular/forms";
import {StorageService} from "../_service/storage.service";

@Component({
  selector: 'app-surgeries',
  templateUrl: './surgeries.component.html',
  styleUrls: ['./surgeries.component.css']
})
export class SurgeriesComponent {
  role = '';
  surgeries$ : Observable<Surgery[]>;
  addSurgeryForm = new FormGroup({
    name: new FormControl(''),
    price: new FormControl('')
  })

  constructor(private surgeryService: SurgeryService,
              private storageService: StorageService) {
    this.surgeries$ = surgeryService.getSurgeries();
    this.role = storageService.getRole();
  }

  addSurgery() {
    const surgery = {
      name: this.addSurgeryForm.value.name,
      price: this.addSurgeryForm.value.price
    }
    this.surgeryService.addSurgery(surgery).subscribe(n => window.location.reload());
  }
}

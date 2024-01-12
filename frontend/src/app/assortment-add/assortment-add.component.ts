import { Component } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {AssortmentService} from "../_service/assortment.service";
import {AssortmentUnit} from "../_model/assortment-unit";
import {AssortmentType} from "../_model/assortment-type";
import {AssortmentCategory} from "../_model/assortment-category";

@Component({
  selector: 'app-assortment-add',
  templateUrl: './assortment-add.component.html',
  styleUrls: ['./assortment-add.component.css']
})
export class AssortmentAddComponent {
  assortmentAddForm = new FormGroup({
    name: new FormControl(''),
    quantity: new FormControl(''),
    volume: new FormControl(''),
    unit: new FormControl(''),
    type: new FormControl(''),
    category: new FormControl('')
  });
  selectedType = '';

  assortmentType = Object.keys(AssortmentType).filter(key => isNaN(Number(key)));
  assortmentUnit = Object.keys(AssortmentUnit).filter(key => isNaN(Number(key)));
  assortmentCategory = Object.keys(AssortmentCategory).filter(key => isNaN(Number(key)));

  constructor(private assortmentService: AssortmentService) {}

  addAssortment() {
    const assortment = {
      name: this.assortmentAddForm.value.name,
      quantity: this.assortmentAddForm.value.quantity,
      volume: this.assortmentAddForm.value.volume,
      unit: this.assortmentAddForm.value.unit,
      type: this.assortmentAddForm.value.type,
      category: this.assortmentAddForm.value.category
    }
    this.assortmentService.addAssortment(assortment).subscribe();
  }

  onTypeChange() {
    this.selectedType = this.assortmentAddForm.value.type ?? '';
  }
}

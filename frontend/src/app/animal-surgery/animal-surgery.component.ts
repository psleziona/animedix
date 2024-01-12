import { Component } from '@angular/core';
import {AnimalSurgery} from "../_model/animal-surgery";
import {AnimalSurgeryService} from "../_service/animal-surgery.service";
import {AssortmentService} from "../_service/assortment.service";
import {Assortment} from "../_model/assortment";
import {ActivatedRoute} from "@angular/router";
import {FormControl} from "@angular/forms";
import {AssortmentType} from "../_model/assortment-type";
import {UsedAssortment} from "../_model/used-assortment";

@Component({
  selector: 'app-animal-surgery',
  templateUrl: './animal-surgery.component.html',
  styleUrls: ['./animal-surgery.component.css']
})
export class AnimalSurgeryComponent {
  surgery?:AnimalSurgery;
  assortments?: Assortment[];
  assortment? : Assortment;
  selectedAssortment = new FormControl('');
  usedQuantityAmount = new FormControl('');
  assortmentType = '';
  unit = '';

  constructor(private animalSurgeryService: AnimalSurgeryService,
              private assortmentService : AssortmentService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    const idSurgery = Number(this.route.snapshot.params['id'])
    this.animalSurgeryService.getSurgery(idSurgery).subscribe(res => this.surgery = res);
    this.assortmentService.getAssortment().subscribe(res => this.assortments = res);
  }

  onAssortmentSelected() {
    const id = this.selectedAssortment.value;
    if(id == '') {
      this.assortmentType = '';
      this.unit = '';
      return;
    }
    // @ts-ignore
    this.assortment = this.assortments?.filter(a => a.idAssortment == Number(id)).at(0);
    //@ts-ignore
    this.assortmentType = this.assortment?.type.toString();
    this.setUnit();
  }

  setUnit() {
    switch (this.assortmentType) {
      case 'COUNTABLE':
        this.unit = 'sztuk';
        break;
      case 'UNCOUNTABLE':
        this.unit = this.assortment?.unit.toString() ?? '';
        break;
    }
  }

  addAssortmentToSurgery() {
    const usedAssortment = {
      idSurgery: this.surgery?.idAnimalSurgery,
      idAssortment: this.assortment?.idAssortment,
    }
    let v = this.usedQuantityAmount.value;
    if(this.assortmentType == 'COUNTABLE')
      { // @ts-ignore
        usedAssortment.quantity = Number(v);
      }
    if(this.assortmentType == 'UNCOUNTABLE')
      { // @ts-ignore
        usedAssortment.volume = Number(v);
      }

    this.animalSurgeryService.addAssortmentToSurgery(usedAssortment).subscribe();
  }

}

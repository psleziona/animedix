import { Component } from '@angular/core';
import {AssortmentService} from "../_service/assortment.service";
import {ActivatedRoute} from "@angular/router";
import {FormControl} from "@angular/forms";
import {Assortment} from "../_model/assortment";
import {StorageService} from "../_service/storage.service";

@Component({
  selector: 'app-assortment',
  templateUrl: './assortment.component.html',
  styleUrls: ['./assortment.component.css']
})
export class AssortmentComponent {
  quantity = new FormControl();
  assortment? : Assortment;
  role = '';
  constructor(private assortmentService: AssortmentService,
              private route: ActivatedRoute,
              private storageService: StorageService) {
    assortmentService.getAssortmentById(this.route.snapshot.params['id']).subscribe(res => this.assortment = res);
    this.role = storageService.getRole();
  }

  fillAssortment() {
    const id = this.route.snapshot.params['id'];
    const value = this.quantity.value;
    this.assortmentService.fillAssortment(id, value).subscribe(res => window.location.reload());
  }
}

import { Component } from '@angular/core';
import {Observable} from "rxjs";
import {Assortment} from "../_model/assortment";
import {AssortmentService} from "../_service/assortment.service";

@Component({
  selector: 'app-assortments',
  templateUrl: './assortments.component.html',
  styleUrls: ['./assortments.component.css']
})
export class AssortmentsComponent {
  assortments$ : Observable<Assortment[]>;

  constructor(private assortmentService: AssortmentService) {
    this.assortments$ = this.assortmentService.getAssortment();
  }
}

import { Component } from '@angular/core';
import {Observable} from "rxjs";
import {Assortment} from "../_model/assortment";
import {AssortmentService} from "../_service/assortment.service";
import {AssortmentType} from "../_model/assortment-type";
import {ActivatedRoute} from "@angular/router";
import {StorageService} from "../_service/storage.service";

@Component({
  selector: 'app-assortments',
  templateUrl: './assortments.component.html',
  styleUrls: ['./assortments.component.css']
})
export class AssortmentsComponent {
  // @ts-ignore
  assortments$ : Observable<Assortment[]>;
  role = '';

  constructor(private assortmentService: AssortmentService, private route: ActivatedRoute,
              private storageService: StorageService) {
    this.route.url.subscribe(segments => {
      if(segments.length > 1 && segments[1].path == 'critical')
        this.assortments$ = this.assortmentService.getAssortmentCritical();
      else
        this.assortments$ = this.assortmentService.getAssortment();
    });
    this.role = storageService.getRole();
  }

}

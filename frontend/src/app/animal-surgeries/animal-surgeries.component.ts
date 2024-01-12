import { Component } from '@angular/core';
import {Observable, Subject} from "rxjs";
import {AnimalSurgery} from "../_model/animal-surgery";
import {StorageService} from "../_service/storage.service";
import {ActivatedRoute} from "@angular/router";
import {VisitService} from "../_service/visit.service";
import {AnimalSurgeryService} from "../_service/animal-surgery.service";

@Component({
  selector: 'app-animal-surgeries',
  templateUrl: './animal-surgeries.component.html',
  styleUrls: ['./animal-surgeries.component.css']
})
export class AnimalSurgeriesComponent {
  animalSurgeries? : AnimalSurgery[];
  currentSurgery?:AnimalSurgery;
  role = '';
  isArchive = false;

  constructor(
    private storageService: StorageService,
    private route: ActivatedRoute,
    private animalSurgeryService: AnimalSurgeryService
  ) {
    this.role = storageService.getRole();
  }

  ngOnInit() {
    this.route.url.subscribe(segments => {
      if(segments.length > 1) {
        const segment = segments[1].path;
        this.isArchive = segment === 'archive';
      }
    });

    this.animalSurgeryService.getSurgeries(this.isArchive).subscribe(
      response => {
        // @ts-ignore
        this.animalSurgeries = response['content'];
      }
    )

    this.animalSurgeryService.getNextSurgery().subscribe(s => this.currentSurgery = s);


  }
}

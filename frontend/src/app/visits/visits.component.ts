import { Component } from '@angular/core';
import {StorageService} from "../_service/storage.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Visit} from "../_model/visit";
import {VisitService} from "../_service/visit.service";

@Component({
  selector: 'app-visits',
  templateUrl: './visits.component.html',
  styleUrls: ['./visits.component.css']
})
export class VisitsComponent {
  role = '';
  isArchive = false;
  visits?: Visit[];
  currentVisit? : Visit;

  constructor(
    private storageService: StorageService,
    private route: ActivatedRoute,
    private visitService: VisitService
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

    this.visitService.getVisits(this.isArchive).subscribe(
      response => // @ts-ignore
        this.visits = response['content']
    );

    this.visitService.getNextVisit().subscribe(visit => this.currentVisit = visit)
  }

}

import { Component } from '@angular/core';
import {VisitService} from "../_service/visit.service";
import {StorageService} from "../_service/storage.service";
import {Visit} from "../_model/visit";
import {ActivatedRoute, Route} from "@angular/router";
import {FormControl} from "@angular/forms";
import {visit} from "@angular/compiler-cli/src/ngtsc/util/src/visitor";

@Component({
  selector: 'app-visit',
  templateUrl: './visit.component.html',
  styleUrls: ['./visit.component.css']
})
export class VisitComponent {
  role = '';
  visit? : Visit;
  doctorRate = new FormControl();
  doctorNote = new FormControl();
  rates = [1,2,3,4,5];
  isPossibleToRate = false;
  isPossibleToAddDoctorComments = false;
  constructor(private visitService: VisitService,
              private storageService: StorageService,
              private route: ActivatedRoute) {

  }

  ngOnInit() {
    this.role = this.storageService.getRole();
    const idVisit = this.route.snapshot.params['id'];
    this.visitService.getVisit(idVisit).subscribe(visit => {
      this.visit = visit;
      const currentDate = new Date();
      const visitDate = new Date(visit.date)
      this.isPossibleToRate = (visitDate < currentDate) && visit.doctorRate == null;
      this.isPossibleToAddDoctorComments = (visitDate < currentDate);
    });
  }

  addDoctorNote() {
    const note = this.doctorNote.value;
    // @ts-ignore
    this.visit.doctorComments = note ?? '';
    // @ts-ignore
    this.visitService.addDoctorComment(this.visit).subscribe(res => window.location.reload())
  }

  rateVisit() {
    const rate =  Number(this.doctorRate.value);
    // @ts-ignore
    const idVisit = this.visit.idVisit;
    // @ts-ignore
    this.visitService.rateVisit(idVisit, rate).subscribe(next => window.location.reload());
  }

}

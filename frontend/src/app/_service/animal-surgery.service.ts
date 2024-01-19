import { Injectable } from '@angular/core';
import {AnimalSurgery} from "../_model/animal-surgery";
import {HttpClient} from "@angular/common/http";
import {StorageService} from "./storage.service";
import {UsedAssortment} from "../_model/used-assortment";

@Injectable({
  providedIn: 'root'
})
export class AnimalSurgeryService {
  url = 'http://localhost:8080/api/animalSurgeries'
  constructor(private http: HttpClient, private storageService: StorageService) { }

  getSurgeries(archive : boolean) {
    return archive ? this.getArchiveSurgeries() : this.getUpcomingSurgeries();
  }

  getUpcomingSurgeries() {
    return this.http.get(this.url + "/upcoming");
  }

  getArchiveSurgeries() {
    return this.http.get(this.url + "/archive");
  }

  getNextSurgery() {
    return this.http.get<AnimalSurgery>(this.url + "/current");
  }

  setAnimalSurgery(surgery: any) {
    return this.http.post(this.url, surgery);
  }

  getOwnSurgeries() {
    const doctorId = this.storageService.getUser()['id'];
    return this.http.get(this.url + '/doctor/' + doctorId);
  }

  getSurgery(idSurgery : number) {
    return this.http.get(this.url + `/${idSurgery}`);
  }

  addAssortmentToSurgery(usedAssortment : any) {
    return this.http.post(this.url + "/assortment", usedAssortment);
  }
}

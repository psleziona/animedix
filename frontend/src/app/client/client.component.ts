import { Component } from '@angular/core';
import {FormControl} from "@angular/forms";
import {ClientService} from "../_service/client.service";
import {StorageService} from "../_service/storage.service";
import {Client} from "../_model/client";
import {ActivatedRoute} from "@angular/router";
import {VisitService} from "../_service/visit.service";

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent {
  password = new FormControl();
  client? : Client;
  visits$;

  constructor(private clientService: ClientService,
              private storageService : StorageService,
              private route: ActivatedRoute) {
    this.clientService.getClient(this.route.snapshot.params['id']).subscribe(res => this.client = res);
    this.visits$ = clientService.getClientVisits(this.route.snapshot.params['id']);
  }

  changePassword() {
    const password = this.password.value;
    this.clientService.changePassword(password).subscribe(res => {
      this.storageService.clean();
      window.location.reload();
    })
  }
}

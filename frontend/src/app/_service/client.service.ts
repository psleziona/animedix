import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Client} from "../_model/client";
import {Visit} from "../_model/visit";

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private url = 'http://localhost:8080/api/clients';
  private authUrl = 'http://localhost:8080/auth';

  constructor(private http: HttpClient) { }

  getClient(id:number) {
    return this.http.get<Client>(this.url + "/" + id);
  }

  changePassword(newPassword: string) {
    let temp = {
      email: '',
      password: newPassword
    }
    return this.http.post(this.authUrl + '/passwordChange', temp);
  }

  getClientVisits(id : number) {
    return this.http.get<Visit[]>(this.url + `/${id}/visits`);
  }
}

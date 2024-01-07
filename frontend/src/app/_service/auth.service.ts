import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {Client} from "../_model/client";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private authUrl = 'http://localhost:8080/auth';
  constructor(private http: HttpClient) { }

  login(body: Object) {
    return this.http.post<any>(this.authUrl + "/login", body);
  }

  register(client: Client) {
    return this.http.post<Client>(this.authUrl + "/register", client);
  }
}

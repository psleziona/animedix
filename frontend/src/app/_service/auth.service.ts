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
  public isLoggedInSubject = new BehaviorSubject<boolean>(false);
  public isLogged$ = this.isLoggedInSubject.asObservable();
  constructor(private http: HttpClient, private router: Router) {
    this.isLoggedInSubject.next(localStorage.getItem('token') != null);
  }

  login(body: Object) {
    this.http.post<any>(this.authUrl + "/login", body).subscribe(
      response => {
        localStorage.setItem('token', response['token']);
        this.isLoggedInSubject.next(true);
        this.router.navigateByUrl('/');
      }
    );
  }

  register(client: Client) {
    this.http.post<Client>(this.authUrl + "/register", client).subscribe()
  }

}

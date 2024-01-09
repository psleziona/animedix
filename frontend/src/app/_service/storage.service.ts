import { Injectable } from '@angular/core';

const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }

  clean() {
    window.sessionStorage.clear();
  }

  public saveUser(user: any) {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser() {
    const user = window.sessionStorage.getItem(USER_KEY);
    if(user)
      return JSON.parse(user);

    return {};
  }

  public getToken() {
    const user = window.sessionStorage.getItem(USER_KEY);
    if(user) {
      const u = JSON.parse(user);
      return u['token'];
    }
  }

  public getExpirationDate() {
    return new Date(this.getUser()['exp'] * 1000);
  }

  public getRole() {
    return this.getUser()['role'];
  }

  public isLoggedIn(): boolean {
    const user = window.sessionStorage.getItem(USER_KEY);
    if(user)
      return true;

    return false;
  }

}

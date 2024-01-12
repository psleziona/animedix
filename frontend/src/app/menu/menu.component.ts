import { Component } from '@angular/core';
import {AuthService} from "../_service/auth.service";
import {StorageService} from "../_service/storage.service";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {
  isAuth = false;
  userRole = '';

  constructor(private storageService: StorageService) {}

  ngOnInit() {
    this.isAuth = this.storageService.isLoggedIn();
    if(this.isAuth) {
      const user = this.storageService.getUser();
      this.userRole = user['role'];
    }

    this.storageService.loggedIn.subscribe(v => {
      if(v) {
        this.isAuth = true;
        const user = this.storageService.getUser();
        this.userRole = user['role'];
      }
    });
  }
  logout() {
    this.storageService.clean();
  }
}

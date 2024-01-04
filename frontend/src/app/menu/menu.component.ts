import { Component } from '@angular/core';
import {AuthService} from "../_service/auth.service";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {
  isAuth = false;
  userRole = '';

  constructor(private authService: AuthService) {}

  ngOnInit() {
    this.authService.isLogged$.subscribe(isAuth => this.isAuth = isAuth);
    if(this.isAuth) {
      const token = localStorage.getItem('token');

      // @ts-ignore
      const tokenParts = token.split('.');
      const encodedPayload = tokenParts[1];

      const decodedPayload = JSON.parse(atob(encodedPayload));
      console.log(decodedPayload);
    }
  }
}

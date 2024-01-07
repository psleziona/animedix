import { Component } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {AuthService} from "../_service/auth.service";
import {Router} from "@angular/router";
import {StorageService} from "../_service/storage.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm = new FormGroup({
    email: new FormControl(''),
    password: new FormControl('')
  })

  constructor(private authService: AuthService,private storageService: StorageService, private router: Router) {}

  login() {
    const body = {
      email: this.loginForm.value.email,
      password: this.loginForm.value.password
    }
    this.authService.login(body).subscribe({
      next: x => {
        const tokenParts = x['token'].split('.');
        const encodedPayload = tokenParts[1];
        const decodedPayload = JSON.parse(atob(encodedPayload));
        decodedPayload['token'] = x['token'];
        this.storageService.saveUser(decodedPayload);
        this.router.navigateByUrl("/animals");
      },
      error: err => console.log(err)
    });
  }
}

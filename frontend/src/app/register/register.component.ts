import { Component } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {AuthService} from "../_service/auth.service";
import {Client} from "../_model/client";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm = new FormGroup({
    forename: new FormControl(''),
    surname: new FormControl(''),
    phone: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl('')
  });

  constructor(private authService: AuthService) {}

  register() {
    const user : Client = {
      animals: [],
      forename: this.registerForm.value.forename ?? '',
      surname: this.registerForm.value.surname ?? '',
      phone: this.registerForm.value.phone ?? '',
      email: this.registerForm.value.email ?? '',
      password: this.registerForm.value.password ?? ''
    }
    this.authService.register(user).subscribe(res => window.location.reload());
  }
}

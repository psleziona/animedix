import {ActivatedRouteSnapshot, CanActivate, CanActivateFn, Router, RouterStateSnapshot} from '@angular/router';
import {Injectable} from "@angular/core";
import {AuthService} from "../_service/auth.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    let isUserLogged;
    this.authService.isLogged$.subscribe(
      isLogged => isUserLogged = isLogged
    )
    if(isUserLogged)
      return true;
    else
      this.router.navigateByUrl("/login");
    return false;
  }
}

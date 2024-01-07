import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Injectable} from "@angular/core";
import {AuthService} from "../_service/auth.service";
import {StorageService} from "../_service/storage.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private storageService: StorageService, private router: Router) {}
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const isUserLoggedIn = this.storageService.isLoggedIn();
    if(isUserLoggedIn)
      return true;
    else
      this.router.navigateByUrl("/login");
    return false;
  }
}

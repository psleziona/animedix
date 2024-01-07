import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpErrorResponse
} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {AuthService} from "../_service/auth.service";
import {StorageService} from "../_service/storage.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private storageService: StorageService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('token');
    if (token) {
      const cloned = req.clone({
        headers: req.headers.set("Authorization", `Bearer ${token}`)
      });

      return next.handle(cloned).pipe(
        catchError((error: HttpErrorResponse) => {
          if (error && error.status === 403) {
            this.storageService.clean();
          }
          return throwError(() => new Error(error.message || 'An unknown error occurred'));
        })
      );
    }

    return next.handle(req);
  }
}

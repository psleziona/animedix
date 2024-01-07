import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpErrorResponse
} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {StorageService} from "../_service/storage.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private storageService: StorageService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.storageService.getUser();
    if (token['token']) {
      const cloned = req.clone({
        setHeaders: {
          Authorization:  `Bearer ${token['token']}`
        }
      });;
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

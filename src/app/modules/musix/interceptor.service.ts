import { Injectable } from '@angular/core';
import { HttpInterceptor,HttpRequest,HttpHandler,HttpEvent } from '@angular/common/http';
import { AuthenticationService } from 'src/app/modules/authentication/authentication.service';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor{

  constructor(private auth:AuthenticationService) { 

  }

  intercept(request:HttpRequest<any>,next:HttpHandler):Observable<HttpEvent<any>>{
      request = request.clone({
        setHeaders: {
         Authorization : `Bearer ${this.auth.getToken()}`
        }
      });
      return next.handle(request);
  }
}

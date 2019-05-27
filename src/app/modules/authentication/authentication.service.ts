import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { load } from '@angular/core/src/render3/instructions';
export const TOKEN="TOKEN";
export const USER_NAME="USER_NAME"

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private registerEndPoint:string;
  private authEndPoint:string;
  constructor(private httpClient:HttpClient) { 
    this.registerEndPoint = 'http://localhost:9005/usertrackservice/api/v1/usertrackservice/register';
    this.authEndPoint = 'http://localhost:9005/authenticationservice/api/v1/userservice/login';
  }

  registerUser(newUser):Observable<any>{
    return this.httpClient.post(this.registerEndPoint,newUser,{observe:"response"});
  }

  

  loginUser(newUser):Observable<any>{
    sessionStorage.setItem(USER_NAME,newUser.userName);
    return this.httpClient.post(this.authEndPoint,newUser,{observe:"response"});
  }

  getToken(){
    return localStorage.getItem(TOKEN);
  }
  
  isTokenExists():boolean{
    const token = this.getToken();
    if(token)
      return true;
    else
      return false;
  }

  logout(){
    sessionStorage.clear();
    localStorage.clear();
  }
  
  

}

import { TestBed } from '@angular/core/testing';

import { AuthenticationService } from './authentication.service';
import {HttpClientTestingModule,HttpTestingController} from '@angular/common/http/testing'

const testData = {
  userName : "test",
  password : "test",
  email : "test@mail.com"
}
describe('AuthenticationService', () => {
  let authService:AuthenticationService;
  let httpTestingController:HttpTestingController;
  const registerEndPoint = 'http://localhost:9005/usertrackservice/api/v1/usertrackservice/register';
  const authEndPoint = 'http://localhost:9005/authenticationservice/api/v1/userservice/login';

  beforeEach(() => 
    {
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule],
      providers:[AuthenticationService]
    });
    authService = TestBed.get(AuthenticationService);
    httpTestingController= TestBed.get(HttpTestingController);
  });

  it('should be created', () => {
    //const service: AuthenticationService = TestBed.get(AuthenticationService);
    expect(authService).toBeTruthy();
  });

  it('registeruser should resond for http',() =>{
    authService.registerUser(testData).subscribe(data =>{
      console.log(data);
      expect(data.body).toBe(testData);
    });
    const httpMockRequest = httpTestingController.expectOne(registerEndPoint);
  
    expect(httpMockRequest.request.method).toBe('POST');
  });

  it('loginruser should resond for http',() =>{
    authService.loginUser(testData).subscribe(data =>{
      console.log(data);
      expect(data.body).toBe(testData);
    });
    const httpMockRequest = httpTestingController.expectOne(authEndPoint);
  
    expect(httpMockRequest.request.method).toBe('POST');
  });

});

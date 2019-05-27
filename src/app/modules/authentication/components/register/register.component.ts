import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/modules/authentication/User';
import {AuthenticationService} from 'src/app/modules/authentication/authentication.service';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user :User;
  constructor(private authService:AuthenticationService, 
    private snackBar:MatSnackBar,
  private router:Router) {
    this.user = new User();
  }

  register(){
    console.log(this.user);
    this.authService.registerUser(this.user).subscribe(data =>{
      if(data.status === 201){
        this.snackBar.open("Successfully Registered","",{
          duration :1000
        });
      }
      this.router.navigate(['/login']);

    },error =>{
      const errMsg = error.error.message;
      this.snackBar.open(errMsg,"",{
        duration:1000
      })}) ;
   }

  ngOnInit() {
  }

}

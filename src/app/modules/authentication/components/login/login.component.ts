import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/modules/authentication/User';
import { AuthenticationService } from 'src/app/modules/authentication/authentication.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';
export const TOKEN="TOKEN";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user : User;
  constructor(private authService:AuthenticationService,
  private router: Router,
  private snackBar:MatSnackBar) { 
    this.user = new User();
  }
  login(){
    console.log(this.user);
    this.authService.loginUser(this.user).subscribe(data =>{
      if(data){
        localStorage.setItem(TOKEN,data.body['token']);
        this.snackBar.open(data.body['message'],"",{
          duration:1000
        });
        this.router.navigate(['/India']);
      }
    },error =>{
      const errorMsg = error.error.message;
      this.snackBar.open(errorMsg,"",{
        duration:1000
      });
    });
  }

  ngOnInit() {
  }

}

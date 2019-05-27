import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterComponent } from './components/register/register.component';
import {AngularMaerialModule} from '../angular-maerial/angular-maerial.module';
import { LoginComponent } from './components/login/login.component';
import {AppRoutingModule} from 'src/app/app-routing.module';
@NgModule({
  declarations: [RegisterComponent, LoginComponent],
  imports: [
    CommonModule,AngularMaerialModule,AppRoutingModule
  ]
})
export class AuthenticationModule { }

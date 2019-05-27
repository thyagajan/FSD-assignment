import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {AuthenticationModule} from 'src/app/modules/authentication/authentication.module';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { MusixModule } from 'src/app/modules/musix/musix.module';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    MusixModule,
    AuthenticationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

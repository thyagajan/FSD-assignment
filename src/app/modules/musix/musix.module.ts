import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { CardContainerComponent } from './components/card-container/card-container.component';
import { CardComponent } from './components/card/card.component';
import { HeaderComponent } from './components/header/header.component';
import { AppRoutingModule } from '../../app-routing.module';
import { WishListComponent } from './components/wish-list/wish-list.component';
import { FooterComponent } from './components/footer/footer.component';
import { DialogComponent } from './components/dialog/dialog.component';
import {AngularMaerialModule} from '../angular-maerial/angular-maerial.module'
import { MusixService } from 'src/app/modules/musix/musix.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { InterceptorService } from 'src/app/modules/musix/interceptor.service';

@NgModule({
  declarations: [ CardContainerComponent, CardComponent, HeaderComponent, 
    WishListComponent, FooterComponent, DialogComponent],
  imports: [
    CommonModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    AngularMaerialModule
  ],
  exports: [
    CardContainerComponent,
    HeaderComponent,
    AppRoutingModule,
    FooterComponent,
  ],
  providers :[
    MusixService,
    {
      provide : HTTP_INTERCEPTORS,
      useClass :InterceptorService,
      multi : true
    }
  ],
  entryComponents: [
    DialogComponent
  ],
})
export class MusixModule { }

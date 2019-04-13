import { DetailComponent } from './detail/detail.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouteReuseStrategy } from '@angular/router';

import { IonicModule, IonicRouteStrategy } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ServiceModule } from './shared/services/service.module';
import { HttpClientModule } from '@angular/common/http';
import { PipesModule } from './shared/pipes/pipe.module';
import { SendComponent } from './send/send.component';
import { FormsModule } from '@angular/forms';
import { EvaluateComponent } from './evaluate/evaluate.component';

@NgModule({
  declarations: [
    AppComponent,
    DetailComponent,
    SendComponent,
    EvaluateComponent
  ],
  entryComponents: [],
  imports: [
    BrowserModule, IonicModule.forRoot({
      backButtonText: '返回'
    }),
    AppRoutingModule,
    HttpClientModule,
    ServiceModule,
    PipesModule,
    FormsModule],
  providers: [
    StatusBar,
    SplashScreen,
    { provide: RouteReuseStrategy, useClass: IonicRouteStrategy }
  ],

  bootstrap: [AppComponent]
})
export class AppModule { }

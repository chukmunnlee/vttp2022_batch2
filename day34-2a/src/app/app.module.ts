import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'

import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpBinService } from './http-bin.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule, HttpClientModule, ReactiveFormsModule
  ],
  providers: [ HttpBinService ],
  bootstrap: [AppComponent]
})
export class AppModule { }

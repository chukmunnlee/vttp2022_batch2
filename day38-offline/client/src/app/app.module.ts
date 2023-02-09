import { NgModule } from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router'
import { HttpClientModule} from '@angular/common/http'

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home.component';
import { TaskRepository } from './task.repository';

const appRoutes: Routes = [
]

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule, RouterModule.forRoot(appRoutes),
	  ReactiveFormsModule, HttpClientModule
  ],
  providers: [ TaskRepository ],
  bootstrap: [AppComponent]
})
export class AppModule { }

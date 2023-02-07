import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router'

import { AppComponent } from './app.component';
import { DogComponent } from './componetns/dog.component';
import { PolarBearComponent } from './componetns/polar-bear.component';
import { HomeComponent } from './componetns/home.component';
import { CustomerComponent } from './componetns/customer.component';
import { ReactiveFormsModule } from '@angular/forms';

// the view / routes
const appRoutes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'dogs', component: DogComponent },
  { path: 'polar-bear', component: PolarBearComponent },
  { path: 'customer/:custName', component: CustomerComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full' }
]

@NgModule({
  declarations: [
    AppComponent,
    DogComponent, PolarBearComponent, HomeComponent, CustomerComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

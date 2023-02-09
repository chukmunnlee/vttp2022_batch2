import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ContactRepository } from './contact.repository';
import { HomeComponent } from './components/home.component';
import { ContactComponent } from './components/contact.component';
import { RouterModule, Routes } from '@angular/router';

const appRoutes: Routes = [
  {
    path: '', component: HomeComponent,
    //canDeactivate: [ ContactRepository ]
  },
  {
    path: 'contact',
    component: ContactComponent,
    canActivate: [ ContactRepository ],
    canDeactivate: [ ContactRepository ]
  },
  { path: '**', redirectTo: '/', pathMatch: 'full' }
]

@NgModule({
  declarations: [
    AppComponent, HomeComponent, ContactComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [ ContactRepository ],
  bootstrap: [AppComponent]
})
export class AppModule { }

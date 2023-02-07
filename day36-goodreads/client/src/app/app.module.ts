import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'
import { RouterModule, Routes } from '@angular/router'

import { AppComponent } from './app.component';
import { MasterComponent } from './components/master.component';
import { DetailsComponent } from './components/details.component';
import { BookService } from './book.service';

const appRoutes: Routes = [
  { path: '', component: MasterComponent },
  { path: 'book/:bookId', component: DetailsComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full' }
]

@NgModule({
  declarations: [
    AppComponent,
    MasterComponent, DetailsComponent
  ],
  imports: [
    BrowserModule, HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [ BookService ],
  bootstrap: [AppComponent]
})
export class AppModule { }

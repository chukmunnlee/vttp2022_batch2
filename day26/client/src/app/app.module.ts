import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http'

import { AppComponent } from './app.component';
import { GenresComponent } from './components/genres.component';
import { TvshowsComponent } from './components/tvshows.component';
import {TvShowService} from './tvshow.service';

const appRoute: Routes = [
	{ path: '', component: GenresComponent },
	{ path: 'tvshow/:genre', component: TvshowsComponent },
	{ path: '**', redirectTo: '/', pathMatch: 'full' }
]

@NgModule({
  declarations: [
    AppComponent,
    GenresComponent, TvshowsComponent
  ],
  imports: [
    BrowserModule, HttpClientModule,
	  RouterModule.forRoot(appRoute, { useHash: true })
  ],
  providers: [ TvShowService ],
  bootstrap: [AppComponent]
})
export class AppModule { }

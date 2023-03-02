import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import {RouterModule, Routes } from '@angular/router';
import {HttpClientModule} from '@angular/common/http';

import { AppComponent } from './app.component';
import { MainComponent } from './components/main.component';
import { BoardComponent } from './components/board.component';
import { UserComponent } from './components/user.component';
import {ReactiveFormsModule} from '@angular/forms';
import {ChatService} from './chat-service';

const appRoutes: Routes = [
	{ path: '', component: MainComponent },
	{ path: 'user', component: UserComponent },
	{ path: 'board', component: BoardComponent },
	{ path: '**', redirectTo: '/', pathMatch: 'full' }
]

@NgModule({
  declarations: [
    AppComponent,
    MainComponent, BoardComponent, UserComponent
  ],
  imports: [
		BrowserModule, ReactiveFormsModule,
		HttpClientModule, RouterModule.forRoot(appRoutes, { useHash: true })
  ],
  providers: [ ChatService ],
  bootstrap: [AppComponent]
})
export class AppModule { }

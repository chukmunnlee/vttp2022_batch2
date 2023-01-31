import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { SearchComponent } from './components/search.component';
import { BGGService } from './bgg.service';
import { DisplayComponent } from './components/display.component';

@NgModule({
  declarations: [
    AppComponent,
    SearchComponent,
    DisplayComponent
  ],
  imports: [
    BrowserModule, BrowserAnimationsModule,
    ReactiveFormsModule, HttpClientModule,
    MaterialModule,
  ],
  providers: [ BGGService ],
  bootstrap: [AppComponent]
})
export class AppModule { }

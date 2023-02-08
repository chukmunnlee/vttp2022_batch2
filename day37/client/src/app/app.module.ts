import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'
import { RouterModule, Routes } from '@angular/router'
import { WebcamModule } from 'ngx-webcam'

import { AppComponent } from './app.component';
import { CameraComponent } from './components/camera.component';
import { UploadComponent } from './components/upload.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CameraService } from './camera.service';

const appsRoute: Routes = [
  { path: '', component: CameraComponent },
  { path: 'upload', component: UploadComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full'}
]

@NgModule({
  declarations: [
    AppComponent,
    CameraComponent,
    UploadComponent
  ],
  imports: [
    BrowserModule, HttpClientModule,
    ReactiveFormsModule, WebcamModule,
    RouterModule.forRoot(appsRoute)
  ],
  providers: [ CameraService ],
  bootstrap: [AppComponent]
})
export class AppModule { }

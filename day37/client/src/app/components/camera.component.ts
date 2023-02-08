import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { WebcamImage } from 'ngx-webcam';
import { Subject } from 'rxjs';
import { CameraService } from '../camera.service';

@Component({
  selector: 'app-camera',
  templateUrl: './camera.component.html',
  styleUrls: ['./camera.component.css']
})
export class CameraComponent {

  width = 400
  height = 400

  constructor(private router: Router, private cameraSvc: CameraService) { }

  trigger = new Subject<void>()

  pics: string[] = []

  snap() {
    this.trigger.next()
  }

  snapshot(img: WebcamImage) {
    console.info('imgAsBase64: ', img.imageAsBase64)
    console.info('imgAsDataUrl: ', img.imageAsDataUrl)
    console.info('imgData: ', img.imageData)

    this.cameraSvc.imageData = img.imageAsDataUrl
    //this.pics.push(img.imageAsDataUrl)

    this.router.navigate(['/upload'])
  }

}

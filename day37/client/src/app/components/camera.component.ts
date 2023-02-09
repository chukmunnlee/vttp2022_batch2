import { AfterViewInit, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { WebcamComponent, WebcamImage } from 'ngx-webcam';
import { Subject, Subscription } from 'rxjs';
import { CameraService } from '../camera.service';

@Component({
  selector: 'app-camera',
  //templateUrl: './camera.component.html',
  templateUrl: './camera_viewchild.component.html',
  styleUrls: ['./camera.component.css']
})
export class CameraComponent implements OnInit, OnDestroy, AfterViewInit {

  // Get a reference of the component
  @ViewChild(WebcamComponent)
  webcam!: WebcamComponent

  width = 400
  height = 400

  constructor(private router: Router, private cameraSvc: CameraService) { }

  trigger = new Subject<void>()
  pics: string[] = []
  sub$!: Subscription

  // Lifecycle listeners
  ngOnInit(): void {
    console.info('>>> onInit: ', this.webcam)
  }
  ngOnDestroy(): void {
    this.sub$.unsubscribe()
  }
  ngAfterViewInit(): void {
    console.info('>>> afterViewInit: ', this.webcam)
    // [trigger]="trigger"
    this.webcam.trigger = this.trigger
    // [width]="100" [height]="100"
    this.webcam.width = 100
    this.webcam.height = 100

    // (imageCapture)="snapshot($event)"
    this.sub$ = this.webcam.imageCapture.subscribe(this.snapshot.bind(this))

    // this.sub$ = this.webcam.imageCapture.subscribe(
    //     (webcamImg: WebcamImage) => {
    //       this.snapshot(webcamImg)
    //     }
    //   )
  }

  snap() {
    this.trigger.next()
  }

  snapshot(img: WebcamImage) {
    console.info('imgAsBase64: ', img.imageAsBase64)
    console.info('imgAsDataUrl: ', img.imageAsDataUrl)
    console.info('imgData: ', img.imageData)

    this.cameraSvc.imageData = img.imageAsDataUrl
    this.pics.push(img.imageAsDataUrl)

    //this.router.navigate(['/upload'])
  }

}

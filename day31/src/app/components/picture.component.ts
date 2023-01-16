import { Component, Input, Output } from '@angular/core';
import { Subject } from 'rxjs';
import { MyImage } from '../model';

@Component({
  selector: 'app-picture',
  templateUrl: './picture.component.html',
  styleUrls: ['./picture.component.css']
})
export class PictureComponent {

  @Input()
  imageURL = "/assets/polar_bear.webp"

  @Input()
  width = 10

  @Output()
  onClicked = new Subject<MyImage>()

  imageClicked() {
    //console.info("image clicked", this.imageURL);
    // fire the event
    const img: MyImage = {
      imageName: this.imageURL,
      size: this.width
    }
    this.onClicked.next(img)
  }

  resize(factor: number) {
    this.width = this.width + factor
  }

  increment() {
    this.width++
  }
  decrement() {
    this.width--
  }

}

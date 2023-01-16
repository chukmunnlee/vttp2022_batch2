import { Component } from '@angular/core';
import { MyImage } from './model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  image = '/assets/3_polar_bears.webp';
  imageWidth = 20

  polarBearImages = [
    "/assets/3_polar_bears.webp",
    "/assets/polar_bear.webp",
  ]

  imageClicked(data: MyImage) {
    console.info(`image clicked: `, data)
  }
}

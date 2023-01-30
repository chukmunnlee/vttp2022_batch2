import { Component, Input } from '@angular/core';
import { Gif } from '../models';

@Component({
  selector: 'app-images',
  templateUrl: './images.component.html',
  styleUrls: ['./images.component.css']
})
export class ImagesComponent {

  @Input()
  gifs: Gif[] = []

}

import { Component, Input } from '@angular/core';
import { Game } from '../models';

@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrls: ['./display.component.css']
})
export class DisplayComponent {

  @Input()
  games: Game[] = []

}

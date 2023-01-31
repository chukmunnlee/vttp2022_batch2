import { Component } from '@angular/core';
import { Game } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  games: Game[] = []

  onResults(games: Game[]) {
    this.games = games
  }
}

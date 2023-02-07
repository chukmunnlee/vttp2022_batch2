import { Component, OnInit } from '@angular/core';
import { BggService } from '../bgg.service';
import { Game } from '../models';

@Component({
  selector: 'app-games',
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.css']
})
export class GamesComponent implements OnInit {

  games: Game[] = []

  constructor(private bggSvc: BggService) { }

  ngOnInit(): void {
    this.bggSvc.getGames()
      .then(result => {
        this.games = result
        console.info('result: ', result)
      })
      .catch(error => {
        console.error('>>> error: ', error)
      })

  }

}

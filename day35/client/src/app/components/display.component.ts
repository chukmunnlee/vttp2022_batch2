import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { BGGService } from '../bgg.service';
import { Game } from '../models';

@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrls: ['./display.component.css']
})
export class DisplayComponent implements OnInit, OnDestroy {

  games: Game[] = []

  sub$!: Subscription

  constructor(private bggSvc: BGGService) { }

  ngOnInit(): void {
    this.sub$ = this.bggSvc.onSearchResults.subscribe(
      (games) => {
        this.games = games
      }
    )
  }

  ngOnDestroy(): void {
    this.sub$.unsubscribe()

  }

}

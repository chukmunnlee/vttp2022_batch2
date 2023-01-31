import { AfterViewInit, ChangeDetectorRef, Component, OnDestroy, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs';
import { SearchComponent } from './components/search.component';
import { DisplayComponent } from './componets/display.component';
import { Game } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit, OnDestroy {

  @ViewChild(SearchComponent)
  searchComp!: SearchComponent

  @ViewChild(DisplayComponent)
  displayComp!: DisplayComponent

  games: Game[] = []

  onResults$!: Subscription

  constructor() {}

  ngAfterViewInit(): void {
    // manually subscribe to the onResults event - @Output
    this.onResults$ = this.searchComp.onResults.subscribe(
      (games: Game[]) => {
        console.info('>>>> in subscription')
        // manually assign the new value to the attribute - @Input()
        this.displayComp.games = games
      }
    )
  }

  ngOnDestroy(): void {
      this.onResults$.unsubscribe()
  }

  onResults(games: Game[]) {
    this.games = games
  }
}

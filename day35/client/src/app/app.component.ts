import { AfterViewInit, ChangeDetectorRef, Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs';
import { SearchComponent } from './components/search.component';
import { DisplayComponent } from './components/display.component';
import { Game } from './models';
import { BGGService } from './bgg.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit, OnInit, OnDestroy {

  searchTerms = ""
  sub$!: Subscription

  constructor(private bggSvc: BGGService) {}

  ngOnInit(): void {
    this.sub$ = this.bggSvc.onSearchQuery.subscribe(
      (name: string) => {
        this.searchTerms = name
      }
    )
  }

  ngOnDestroy(): void {
    this.sub$.unsubscribe()
  }

  ngAfterViewInit(): void {
  }
}

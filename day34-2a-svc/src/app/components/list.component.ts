import { Component, Input, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';
import { Subscription } from 'rxjs';
import { FriendsService } from '../friends.service';
import { User } from '../models';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnChanges, OnInit, OnDestroy {

  @Input()
  friends: User[] = []

  @Input()
  name = "not set"

  count = 0

  friend$!: Subscription

  constructor(private friendsSvc: FriendsService) {}

  ngOnInit(): void {
    this.friend$ = this.friendsSvc.friends.subscribe(
        (data: User) => {
          console.info('>>>> from friendsSvc: ', data)
        }
      )
  }

  ngOnDestroy(): void {
      this.friend$.unsubscribe()
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.info('----- onChanges')
    console.info('>>> ', changes)
    this.count = changes['friends'].currentValue.length
  }

}

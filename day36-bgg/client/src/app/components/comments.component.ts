import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { BggService } from '../bgg.service';
import { Comment } from '../models';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit, OnDestroy {

  params$!: Subscription
  comments: Comment[] = []

  constructor(private router: Router, private activatedRoute: ActivatedRoute
      , private bggSvc: BggService) { }

  ngOnInit(): void {
    this.params$ = this.activatedRoute.params.subscribe(
      params => {
        const gameId = params['gameId']
        this.bggSvc.getComments(gameId)
          .then(result => {
            this.comments = result
            console.info('>>> comments: ', this.comments)
          })
          .catch(error => {
            console.error('>> error: ', error)
          })
      }
    )
  }

  ngOnDestroy(): void {

  }

}

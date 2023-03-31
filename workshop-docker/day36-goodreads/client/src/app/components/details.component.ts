import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { BookService } from '../book.service';
import { Book } from '../models';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {

  params$!: Subscription
  book!: Book

  constructor(private activatedRoute: ActivatedRoute, private bookSvc: BookService) { }

  ngOnInit(): void {

    this.params$ = this.activatedRoute.params.subscribe(
      (params) => {
        const bookId = params['bookId']
        this.bookSvc.getBookById(bookId)
          .then(result => {
            this.book = result
            console.info('>>> book: ', this.book)
          })
          .catch(error => {
            console.error('>> error: ', error)
          })
      }
    )

  }

}

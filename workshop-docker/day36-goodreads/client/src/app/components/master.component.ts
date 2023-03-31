import { Component, OnInit } from '@angular/core';
import { BookService } from '../book.service';
import { BookSummary } from '../models';

@Component({
  selector: 'app-master',
  templateUrl: './master.component.html',
  styleUrls: ['./master.component.css']
})
export class MasterComponent implements OnInit {

  books: BookSummary[] = []

  constructor(private bookSvc: BookService) { }

  ngOnInit(): void {
      this.bookSvc.getBooks()
        .then(books => {
          this.books = books
          console.info('>>> books: ', this.books)
        })
        .catch(error => {
          console.error('>> error: ', error)
        })
  }

}

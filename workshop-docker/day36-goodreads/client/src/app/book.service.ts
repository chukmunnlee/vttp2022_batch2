import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom } from "rxjs";
import { BookSummary, Book } from "./models";

@Injectable()
export class BookService {

  constructor(private http: HttpClient) { }

  getBooks(): Promise<BookSummary[]> {
    return firstValueFrom(
      this.http.get<BookSummary[]>('/api/books')
    )
  }

  getBookById(bookId: string): Promise<Book> {
    return firstValueFrom(
      this.http.get<Book>(`/api/book/${bookId}`)
    )

  }
}



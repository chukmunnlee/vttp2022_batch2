import { Component, OnInit } from '@angular/core';
import { Todo } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  todo: Todo|null = null

  ngOnInit(): void {
    const jsonString = sessionStorage.getItem('todo')
    if (!!jsonString)
      this.todo = JSON.parse(jsonString)

    console.info('>>> in ngOnInit: ', this.todo)
  }

  processNewTodo(todo: Todo) {
    const jsonString = JSON.stringify(todo)
    console.info('>> saving todo to localstorage')
    sessionStorage.setItem('todo', jsonString)
  }
}

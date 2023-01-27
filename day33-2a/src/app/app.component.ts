import { Component } from '@angular/core';
import { Todo } from './models';

const TODO: Todo = {
  name: 'barney',
  email: 'barney@gmail.com',
  tasks: [
    { task: 'jogging', priority: 'high'},
    { task: 'lunch with friends', priority: 'high'},
  ]
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  data: Todo = TODO

  processTodo(todo: Todo) {
    console.info('in process todo')
    console.info('>>>> ', todo)
  }
}

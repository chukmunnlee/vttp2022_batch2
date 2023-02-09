import { Component } from '@angular/core';
import { TaskRepository } from './task.repository';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private taskRepo: TaskRepository) { }

  sync() {

  }
}

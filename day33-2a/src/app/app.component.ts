import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { TodoComponent } from './components/todo.component';
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
export class AppComponent implements OnInit, AfterViewInit {

  @ViewChild(TodoComponent)
  todoComp!: TodoComponent

  @ViewChild('clearBtn')
  clearBtn!: ElementRef

  data: Todo = TODO

  ngOnInit(): void {
    console.info('onInit: ', this.todoComp)
    console.info('onInit clearBtn: ', this.clearBtn)

  }

  ngAfterViewInit(): void {
    console.info('afterViewInit: ', this.todoComp)
    console.info('afterViewInit clearBtn: ', this.clearBtn)
  }

  newTodo(todo: Todo) {
    console.info('in new todo')
    console.info('>>>> ', todo)
  }

  processTodo() {
    this.data = this.todoComp.value()
    this.todoComp.clearForm()
    console.info('in process todo: ', this.data)
    this.clearBtn.nativeElement.innerText = `${this.data.name} - ${this.data.email}`
    this.clearBtn.nativeElement.disabled = true
  }
}

import { Component, Input, OnInit, Output } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { Todo, Task } from '../models';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {

  @Output()
  onNewTodo = new Subject<Todo>()

  @Input()
  todo: Todo | null = null

  todoForm!: FormGroup
  taskArray!: FormArray

  // @Autowire
  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.todoForm = this.createForm(this.todo)
  }

  processForm() {
    const todo: Todo = this.todoForm.value as Todo
    console.info('>>> todo: ', todo)
    this.onNewTodo.next(todo)
    this.todoForm = this.createForm()
  }

  addTask() {
    this.taskArray.push(this.createTask())
  }

  removeTask(i: number) {
    this.taskArray.removeAt(i)
  }

  value(): Todo {
    return this.todoForm.value as Todo
  }

  invalid(): boolean {
    return this.todoForm.invalid || this.taskArray.length <= 0
  }

  clearForm() {
    this.todoForm = this.createForm()
  }

  private createTask(task: Task | null = null): FormGroup {
    return this.fb.group({
      task: this.fb.control(task?.task? task.task: ''
          , [ Validators.required ]),
      priority: this.fb.control(task?.priority? task.priority: 'med')
    })
  }

  private createTasks(tasks: Task[] = []): FormArray {
    // Returns an array of FormGroup
    return this.fb.array(
      tasks.map(t => this.createTask(t))
    )
  }

  private createForm(todo: Todo | null = null): FormGroup {

    //this.taskArray = this.createTasks( this.todo?.tasks? this.todo.tasks: [])
    if (this.todo?.tasks)
      this.taskArray = this.createTasks(todo?.tasks)
    else
      this.taskArray = this.createTasks([])

    return this.fb.group({
      name: this.fb.control(todo?.name ? todo.name: ''
          , [ Validators.required, Validators.minLength(3) ]),
      email: this.fb.control(todo?.email ? todo.email: ''
          , [ Validators.required, Validators.email ]),
      tasks: this.taskArray
    })
  }
}

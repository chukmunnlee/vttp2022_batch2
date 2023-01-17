import { Component, Input, OnInit, Output } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { Task, Todo } from '../models';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {

  @Output()
  onNewTodo = new Subject<Todo>()

  @Input()
  todo: Todo|null = null

  todoForm!: FormGroup
  taskArray!: FormArray

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.todoForm = this.createTodo(this.todo)
  }

  processForm() {
    const todo = this.todoForm.value as Todo
    console.info('>>> todo: ', todo)
    this.onNewTodo.next(todo);
  }

  addTask() {
    this.taskArray.push(this.createTask())
  }

  deleteTask(i: number) {
    this.taskArray.removeAt(i)
  }

  private createTodo(todo: Todo|null = null): FormGroup {
    this.taskArray = this.createTasks(todo?.tasks? todo.tasks: []);
    return this.fb.group({
      name: this.fb.control(todo?.name? todo.name: ''
          , [ Validators.required ]),
      email: this.fb.control(todo?.email? todo.email: ''
          , [ Validators.required, Validators.email ]),
      tasks: this.taskArray
    })
  }

  private createTasks(tasks:Task[] = []): FormArray {
    return this.fb.array(
      tasks.map(t => this.createTask(t))
    )
  }

  private createTask(task: Task|null = null): FormGroup {
    return this.fb.group({
      task: this.fb.control(task?.task? task.task: '', [Validators.required]),
      priority: this.fb.control(task?.priority? task.priority: ''),
      dueDate: this.fb.control(task?.dueDate? task.dueDate: ''),
    })
  }
}

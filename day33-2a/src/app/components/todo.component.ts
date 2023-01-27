import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Todo } from '../models';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {

  todoForm!: FormGroup
  taskArray!: FormArray

  // @Autowire
  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.todoForm = this.createForm()
  }

  processForm() {
    const todo: Todo = this.todoForm.value as Todo
    console.info('>>> todo: ', todo)
  }

  addTask() {
    this.taskArray.push(this.createTask())
  }

  removeTask(i: number) {
    this.taskArray.removeAt(i)
  }

  invalid(): boolean {
    return this.todoForm.invalid || this.taskArray.length <= 0
  }

  clearForm() {
    this.todoForm = this.createForm()
  }

  private createTask(): FormGroup {
    return this.fb.group({
      task: this.fb.control('', [ Validators.required ]),
      priority: this.fb.control('med')
    })
  }

  private createForm(): FormGroup {
    this.taskArray = this.fb.array([], [ Validators.minLength(1) ])
    return this.fb.group({
      name: this.fb.control('', [ Validators.required, Validators.minLength(3) ]),
      email: this.fb.control('', [ Validators.required, Validators.email ]),
      tasks: this.taskArray
    })
  }
}

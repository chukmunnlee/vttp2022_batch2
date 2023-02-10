import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Task} from '../models';
import { TaskRepository } from '../task.repository';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

	form!: FormGroup

	constructor(private fb: FormBuilder, private taskRepo: TaskRepository) { }

	ngOnInit(): void {
		this.form = this.createForm()
	}

	processTask() {
		const task = this.form.value as Task
    console.info('>>>> task: ', task)
		task.dueDate = new Date(this.form.get('dueDate')?.value).getTime()
    this.taskRepo.addTodo(task)
      .then(result => {
        this.ngOnInit()
        return this.taskRepo.getTodos()
      })
	}

  invalid() {
    const d = new Date()
    const now = new Date(`${d.getFullYear()}-${d.getMonth()}-${d.getDate()}`).getTime()
    const dueDate = (new Date(this.form.get('dueDate')?.value)).getTime()
    return this.form.invalid || (dueDate < now)
  }

	private createForm(): FormGroup {
		return this.fb.group({
			task: this.fb.control<string>('', [ Validators.required, Validators.minLength(3) ]),
			priority: this.fb.control<number>(1, [ Validators.required, Validators.min(1), Validators.max(5) ]),
			dueDate: this.fb.control<number>(Date.now(), [ Validators.required ])
		})
	}

}

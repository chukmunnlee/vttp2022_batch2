import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Task} from '../models';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

	form!: FormGroup

	constructor(private fb: FormBuilder) { }

	ngOnInit(): void {
		this.form = this.createForm()
	}

	processTask() {
		const task = this.form.value as Task
		console.info('>>> task: ', task)
	}

	private createForm(): FormGroup {
		return this.fb.group({
			task: this.fb.control<string>('', [ Validators.required, Validators.minLength(3) ]),
			priority: this.fb.control<number>(1, [ Validators.required, Validators.min(1), Validators.max(5) ]),
			dueDate: this.fb.control<any>('', [ Validators.required ])
		})
	}

}

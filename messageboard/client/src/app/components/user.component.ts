import { Component, OnDestroy, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators } from '@angular/forms';
import {ChatService} from '../chat-service';
import {ChatMessage} from '../models';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit, OnDestroy {

	form!: FormGroup

	constructor(private chatSvc: ChatService, private fb: FormBuilder) { }

	ngOnInit(): void {
		console.info('>>> connecting...')
		this.chatSvc.connect()
			.catch(error => {
				console.error('>>> connection error: ', error)
			})

		this.form = this.fb.group({
			name: this.fb.control('', [Validators.required, Validators.minLength(2)]),
			message: this.fb.control('', [Validators.required, Validators.minLength(2)]),
		})
	}
	
	ngOnDestroy(): void {
		console.info('>>> disconnecting...')
		this.chatSvc.disconnect()
	}

	send() {
		const msg: ChatMessage = {
			sender: this.form.get('name')?.value,
			message: this.form.get('message')?.value
		}

		this.chatSvc.send(msg)

		this.form.get('message')?.reset()
	}

}

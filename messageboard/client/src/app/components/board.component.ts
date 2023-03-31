import { Component, OnDestroy, OnInit } from '@angular/core';
import {Subscription} from 'rxjs';
import {ChatService} from '../chat-service';
import {ChatMessage} from '../models';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit, OnDestroy {

	sub$!: Subscription

	messages: ChatMessage[] = []

	constructor(private chatSvc: ChatService) { }

	ngOnInit(): void {
		this.chatSvc.connect()
			.then(() => {
				this.sub$ = this.chatSvc.onMessages.subscribe(
					(chatMsg: ChatMessage) => {
						this.messages.unshift(chatMsg)
					}
				)
			})
			.catch(error => {
				console.error('Connection error: ', error)
			})
	}

	ngOnDestroy(): void {
		this.sub$.unsubscribe()
	}

}

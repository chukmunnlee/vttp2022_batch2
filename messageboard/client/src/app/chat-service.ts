import {Injectable} from "@angular/core";
import {Subject} from "rxjs";

import  * as SockJS from "sockjs-client";
import * as Stomp from 'stompjs'
import {ChatMessage} from "./models";

const WS_ENDPOINT = "http://localhost:8080/ws"

@Injectable()
export class ChatService {

	sock!: WebSocket
	client!: Stomp.Client
	sub$!: Stomp.Subscription

	onMessages = new Subject<ChatMessage>()

	connect(endpoint = WS_ENDPOINT): Promise<Stomp.Frame | string> {
		const headers = {
			login: 'fred',
			passcode: 'fred',
			challenge: 'abc123'
		}
		this.sock = new SockJS(endpoint)
		this.client = Stomp.over(this.sock)
		return new Promise<any>((resolve, reject) => {
			this.client.connect(headers,
				(frame: Stomp.Frame | undefined) => {
					console.info('>>> connection callback: ', frame)
					this.sub$ = this.client.subscribe('/topic/greetings', 
						(payload: Stomp.Message) => {
							const chatMsg = JSON.parse(payload.body) as ChatMessage
							this.onMessages.next(chatMsg)
						}
					)
					resolve(frame)
				},
				(error: Stomp.Frame | string) => {
					console.error('>>> error callback: ', error)
					reject(error)
				})
		})
	}

	send(msg: ChatMessage) {
		this.client.send("/app/greetings", {}, JSON.stringify(msg))
	}

	disconnect() {
		this.sub$.unsubscribe()
		this.client.disconnect(() => {
			console.info('Socket disconnected')
		})
	}
}


import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent {

	message: string[] = []

	constructor(private router: Router) {}

	navigateTo(view: string) {
		console.info('>>> view: ', view)
		this.router.navigate([ '/', view ])
	}
}

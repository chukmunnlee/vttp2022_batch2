import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  selected(inventory: string) {
    console.info('>>> app.component selected: ', inventory)
  }
}

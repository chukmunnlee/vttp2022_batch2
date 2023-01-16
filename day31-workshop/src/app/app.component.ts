import { Component } from '@angular/core';
import { INVENTORIES } from './constant';
import { Inventory } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  content: Inventory[] = []

  selected(inventory: string) {
    console.info('>>> app.component selected: ', inventory)
    // filter returns an array
    const inv = INVENTORIES.filter(v => v.imageUrl == inventory)
    this.content.push(inv[0])
  }
}

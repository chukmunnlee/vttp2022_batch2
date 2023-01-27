import { Component } from '@angular/core';
import { Order } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  order!: Order

  customerName!: string

  processNewOrder(o: Order) {
    this.order = o
    this.customerName = o.name
    console.info('>>> in AppComponent: ', this.order)
  }

}

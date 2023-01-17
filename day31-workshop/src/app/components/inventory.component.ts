import { Component, Output } from '@angular/core';
import { Subject } from 'rxjs';
import { INVENTORIES } from '../constant';
import { CustomerSelection, Inventory } from '../models';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent {

  @Output()
  onSelection = new Subject<CustomerSelection>()

  quantity = 0

  inventories: Inventory[] = INVENTORIES

  selected(name: string) {
    const selection = {
      name,
      quantity: this.quantity
    } as CustomerSelection
    this.onSelection.next(selection)
  }

  addTo(i: number) {
    this.quantity += i
  }

}

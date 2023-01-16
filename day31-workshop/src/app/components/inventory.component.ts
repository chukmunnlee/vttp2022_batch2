import { Component, Output } from '@angular/core';
import { Subject } from 'rxjs';
import { INVENTORIES } from '../constant';
import { Inventory } from '../models';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent {

  @Output()
  onSelection = new Subject<string>()

  inventories: Inventory[] = INVENTORIES

  selected(token: string) {
    this.onSelection.next(token)
  }

}

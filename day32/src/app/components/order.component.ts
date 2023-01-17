import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Order } from '../models';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  form!: FormGroup
  lineItems!: FormArray
  rushFieldName = 'rush'
  name = "fred"
  order!: Order

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    // Build the form
    this.form = this.createForm()
  }

  process() {
    this.order = this.form.value as Order
    console.info('>>> form: ', this.order)
  }

  clearForm() {
    // clear the form
    // this.form.reset()
    this.form = this.createForm()
  }

  public addItem() {
    this.lineItems.push(this.createLineItem())
  }
  public deleteItem(i: number) {
    this.lineItems.removeAt(i)
  }

  private createForm(): FormGroup {
    this.lineItems = this.fb.array([])
    return this.fb.group({
      name: this.fb.control<string>('fred', [ Validators.required, Validators.minLength(3) ]),
      email: this.fb.control<string>('fred@gmail.com', [ Validators.required, Validators.email ]),
      rush: this.fb.control<boolean>(true),
      lineItems: this.lineItems
    })
  }

  private createLineItem(): FormGroup {
    return this.fb.group({
      item: this.fb.control<string>('', [ Validators.required ]),
      quantity: this.fb.control<number>(1,
          [ Validators.required, Validators.min(1), Validators.max(100) ])
    })
  }
}

import { Component, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { User } from '../models';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  @Output()
  onNewUser = new Subject<User>()

  form!: FormGroup

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.form = this.createForm()
  }

  processForm() {
    console.info('>> form: ', this.form.value)
    this.onNewUser.next(this.value())
  }

  value(): User {
    return this.form.value as User
  }

  clearForm() {
    this.form = this.createForm()
  }

  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>('', [ Validators.required, Validators.minLength(3)] ),
      email: this.fb.control<string>('', [ Validators.required, Validators.email] ),
      phone: this.fb.control<string>('', [ Validators.required, Validators.minLength(8) ] ),
      dob: this.fb.control<Date>(new Date(), [ Validators.required ] ),
    })
  }
}

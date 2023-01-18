import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  form!: FormGroup

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.form = this.createForm()
  }

  processForm() {
    console.info('>> form: ', this.form.value)
  }

  clearForm() {
    this.form = this.createForm()
  }

  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>('', [ Validators.required, Validators.minLength(3)] ),
      email: this.fb.control<string>('', [ Validators.required, Validators.email] ),
      dob: this.fb.control<Date>(new Date(), [ Validators.required ] ),
    })
  }

}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  form!: FormGroup

  constructor(private fb: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.form = this.createForm();
  }

  add() {
    const name = this.form.value['name']
    console.info('>>>> name: ', name)
    this.router.navigate([ '/customer', name ])
    this.form = this.createForm();
  }

  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control('')
    })
  }
}

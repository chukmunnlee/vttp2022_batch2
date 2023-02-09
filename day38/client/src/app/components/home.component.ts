import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ContactRepository } from '../contact.repository';
import { CanLeave } from '../models';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, CanLeave {

  form!: FormGroup

  constructor(private fb: FormBuilder, private router: Router
        , private contactRepo: ContactRepository) {}

  ngOnInit(): void {

    this.form = this.fb.group({
      name: this.fb.control('', [ Validators.required ])
    })
  }

  canLeave(): boolean {
      return this.form.valid
  }

  login() {
    this.contactRepo.canLogin = this.form.get('name')?.value == "fred"
    console.info('>>> canLogin: ', this.contactRepo.canLogin)
    this.router.navigate([ '/contact' ])
  }

}

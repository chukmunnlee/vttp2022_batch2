import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ContactRepository } from '../contact.repository';
import { CanLeave, Contact } from '../models';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit, CanLeave {

  form!: FormGroup

  contacts: Contact[] = []

  constructor(private fb: FormBuilder, private router: Router,
      private contactRepo: ContactRepository) { }

  async ngOnInit() {

    this.form = this.fb.group({
      name: this.fb.control(''),
      email: this.fb.control(''),
    })

    this.contacts = await this.contactRepo.getAllContacts();

  }

  async addContact() {
    const contact = this.form.value as Contact
    console.info('>>> contact: ', contact)
    try {
      const k = await this.contactRepo.addContact(contact)
      console.info('> k ', k)
      this.contacts = await this.contactRepo.getAllContacts()
      // clear the for first
      this.ngOnInit()
      this.router.navigate([ '/' ])
    } catch (error) {
      console.error('error: ', error)
    }

  }

  // CanLeave interface
  canLeave(): boolean {
    return this.form.dirty
  }

  // addContact() {
  //   const contact = this.form.value as Contact
  //   console.info('>>> contact: ', contact)
  //   this.contactRepo.addContact(contact)
  //     .then(v => {
  //       console.info('>>> v: ', v)
  //       this.ngOnInit()
  //     })
  //     .catch(error => {
  //       console.error("error: ", error)
  //     })
  // }
}

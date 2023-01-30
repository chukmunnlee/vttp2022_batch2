import { Component, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Subject } from 'rxjs';
import { FriendsService } from '../friends.service';
import { User } from '../models';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  form!: FormGroup

  @Output()
  onNewUser = new Subject<User>()

  constructor(private fb: FormBuilder, private friendsSvc: FriendsService) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      name: this.fb.control('')
    })
  }

  processForm() {
    const data: User = this.form.value
    this.onNewUser.next(data)
    this.friendsSvc.friends.next(data)
    this.ngOnInit()
  }

}

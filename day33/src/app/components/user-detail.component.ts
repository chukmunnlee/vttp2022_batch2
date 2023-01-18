import { Component, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Subject } from 'rxjs';
import { UserDetail } from '../models';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {

  @Output()
  onUserDetail = new Subject<UserDetail>()

  form!: FormGroup

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.form = this.createForm()
  }

  getFormValue(): UserDetail {
    return this.form.value as UserDetail
  }

  processForm() {
    const userDetail: UserDetail = this.form.value as UserDetail
    console.info('user detail: ', userDetail)
    this.onUserDetail.next(userDetail);
  }

  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control(''),
      email: this.fb.control(''),
      comments: this.fb.control(''),
    })
  }

}

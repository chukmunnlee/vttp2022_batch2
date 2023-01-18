import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { UserDetailComponent } from './components/user-detail.component';
import { UserDetail } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterViewInit {

  users: UserDetail[] = []

  newName!: string

  //@ViewChild('userDetail')
  @ViewChild(UserDetailComponent)
  userDetail!: UserDetailComponent

  @ViewChild('h2')
  h2!: ElementRef

  ngOnInit(): void {
    console.info('>>> ngOnInit userDetail: ', this.userDetail)
  }
  ngAfterViewInit(): void {
    console.info('>>> ngAfterViewInit userDetail: ', this.userDetail)
    console.info('>>> ngAfterViewInit h2: ', this.h2)
  }

  process(userDetail: UserDetail) {
    console.info('>>>> appcomponent: ', userDetail)
    this.newName = userDetail.name
    this.users = [ ...this.users, userDetail ]

    // this.users.push(userDetail)
    // const u: UserDetail[] = []
    // for (let n of this.users)
    //   u.push(n)
    // this.users = u
  }

  deleteUser() {
    console.info('deleting user')
    const user = this.userDetail.getFormValue()
    console.info('form value: ', user)
    this.h2.nativeElement.innerText = user.name
  }
  updateUser() {
    console.info('updating user')
  }
}

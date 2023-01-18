import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { UserComponent } from './components/user.component';
import { User } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterViewInit {

  @ViewChild(UserComponent)
  userComponent!: UserComponent

  users: User[] = []
  canShare = false

  constructor() {}

  ngOnInit(): void {
    this.canShare = false;
  }

  ngAfterViewInit(): void {
  }

  newUser(user: User) {
    this.users = [ ...this.users, user ]
  }

  clearForm() {
    this.userComponent.clearForm()
  }

  share() {
    console.info('>>>> user: ', this.userComponent.value())
  }

}

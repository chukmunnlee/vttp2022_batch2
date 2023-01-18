import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { UserComponent } from './components/user.component';
import { NgNavigatorShareService } from 'ng-navigator-share'
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

  constructor(private webshare: NgNavigatorShareService) {}

  ngOnInit(): void {
    this.canShare = this.webshare.canShare()
    console.info('can share: ', this.canShare)
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
    const user = this.userComponent.value()
    console.info('>>>> user: ', user)
    this.webshare.share({
      title: user.name,
      text: `Email: ${user.email}, Phone: ${user.phone}`
    })
    .then(result => {
      console.info('>>> share result: ', result)
    })
    .catch(err => {
      console.error('>>> share error: ', err)
    })
  }

}

import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-polar-bear',
  templateUrl: './polar-bear.component.html',
  styleUrls: ['./polar-bear.component.css']
})
export class PolarBearComponent {

  constructor(private router: Router) { }

  process() {
    console.info('>>> process some data')
    this.router.navigate([ '/' ])
  }

}

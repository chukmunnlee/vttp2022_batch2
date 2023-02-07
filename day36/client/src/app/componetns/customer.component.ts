import { Component, OnDestroy, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit, OnDestroy {

  custName = ''

  param$!: Subscription

  constructor(private activateRoute: ActivatedRoute, private title: Title) { }

  ngOnInit(): void {
      // this.custName = this.activateRoute.snapshot.params['custName']
      // this.title.setTitle(`Customer ${this.custName}`)
      this.param$ = this.activateRoute.params.subscribe(
        (params) => {
          console.info('>>> v = ', params)
          this.custName = params['custName']
          this.title.setTitle(`Customer ${this.custName}`)
        }
      )
  }

  ngOnDestroy(): void {
      console.info('+++ Customer component destroyed')
      this.param$.unsubscribe()
  }

}

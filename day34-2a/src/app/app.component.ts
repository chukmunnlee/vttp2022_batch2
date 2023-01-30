import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { filter, firstValueFrom, last, map, take } from 'rxjs';
import { HttpBinService } from './http-bin.service';
import { UserData } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  data!: UserData
  form!: FormGroup

  constructor(private http: HttpClient, private fb: FormBuilder
      , private httpBinSvc: HttpBinService) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      name: this.fb.control(''),
      email: this.fb.control(''),
    })
  }

  processForm() {
    const formData: UserData = this.form.value
    this.httpBinSvc.doGet(formData)
      .then(result => {
        console.info('>>> in then')
        this.data = result
      })
      .catch(error => {
        console.info('>>> in error')
        console.error('>>> error: ', error)
        this.data = error
      })
      .finally(() => {
        console.info('>>> in finally')
        this.ngOnInit()
      })
  }

}

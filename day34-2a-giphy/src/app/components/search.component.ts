import { Component, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { SearchCriteria } from '../models';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  @Output()
  onSearch = new Subject<SearchCriteria>()

  searchForm!: FormGroup

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.searchForm = this.createForm()
  }

  processForm() {
    const searchCriterial: SearchCriteria = this.searchForm.value
    searchCriterial.count = parseInt(this.searchForm.value.count)
    this.onSearch.next(searchCriterial)
    this.searchForm = this.createForm()
  }

  private createForm(): FormGroup {
    return this.fb.group({
      text: this.fb.control<string>('', [ Validators.required ]),
      count: this.fb.control<number>(5)
    })
  }

}

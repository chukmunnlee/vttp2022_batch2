import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  searchForm!: FormGroup

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.searchForm = this.createForm()
  }

  doSearch() {
    const name = this.searchForm.get('name')?.value
    console.info('>>>> name: ', name)
  }

  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control('', [ Validators.required ])
    })
  }

}

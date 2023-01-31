import { Component, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { BGGService } from '../bgg.service';
import { Game } from '../models';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  @Output()
  onResults = new Subject<Game[]>

  searchForm!: FormGroup

  constructor(private fb: FormBuilder, private bggSvc: BGGService) { }

  ngOnInit(): void {
    this.searchForm = this.createForm()
  }

  doSearch() {
    const name = this.searchForm.get('name')?.value
    console.info('>>>> name: ', name)
    this.bggSvc.searchGameByName(name)
      .then(games => {
        console.info('>>> games: ', games)
        this.onResults.next(games)
        this.searchForm.reset()
      })
      .catch(error => {
        console.error('>>> error: ', error)
      })
  }

  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control('', [ Validators.required ])
    })
  }

}

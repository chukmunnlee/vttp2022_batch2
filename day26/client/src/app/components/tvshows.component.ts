import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {TVShow} from '../models';
import {TvShowService} from '../tvshow.service';

@Component({
  selector: 'app-tvshows',
  templateUrl: './tvshows.component.html',
  styleUrls: ['./tvshows.component.css']
})
export class TvshowsComponent implements OnInit {

	tvshows: TVShow[] = []
	genre = "";

	constructor(private tvshowSvc: TvShowService, private activatedRoute: ActivatedRoute) { }

	ngOnInit(): void {
		this.genre = this.activatedRoute.snapshot.params['genre']
		console.info('genre: ', this.genre)

		this.tvshowSvc.getTVShowByGenre(this.genre)
			.then(result => {
				this.tvshows = result
			})
			.catch(err => {
				console.error(">>> err: ", err)
			})
		
	}

}

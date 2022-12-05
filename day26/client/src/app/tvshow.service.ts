import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {firstValueFrom} from "rxjs";
import {TVShow} from "./models";

@Injectable()
export class TvShowService {

	constructor(private http: HttpClient) { }

	getGenres(): Promise<string[]> {
		return firstValueFrom(
			this.http.get<string[]>('/api/genres')
		)
	}

	getTVShowByGenre(genre: string): Promise<TVShow[]> {
		return firstValueFrom(
			this.http.get<TVShow[]>(`/api/tvshow/${genre}`)
		)
	}

}

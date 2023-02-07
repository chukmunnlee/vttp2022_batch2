import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { lastValueFrom } from "rxjs";
import { Game } from "./models";

@Injectable()
export class BggService {

  constructor(private http: HttpClient) { }

  getGames(): Promise<Game[]> {
    return lastValueFrom(
      this.http.get<Game[]>('/api/games')
    )
  }
}


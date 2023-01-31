import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom } from "rxjs";
import { Game } from "./models";

const BACKEND = 'http://localhost:8080'

@Injectable()
export class BGGService {

  constructor(private http: HttpClient) { }

  searchGameByName(name: string): Promise<Game[]> {
    const params = new HttpParams().set("name", name)
    return firstValueFrom(
      this.http.get<Game[]>(`${BACKEND}/api/games`, { params })
    )
  }

}

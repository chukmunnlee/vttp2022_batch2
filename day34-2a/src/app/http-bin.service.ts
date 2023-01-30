import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom, map, take } from "rxjs";
import { UserData } from "./models";

@Injectable()
export class HttpBinService {

  constructor(private http: HttpClient) { }

  doGet(data: UserData): Promise<UserData> {
    let qs = new HttpParams()
        .set("name", data.name)
        .set("email", data.email)

    return firstValueFrom<UserData>(
      this.http.get<any>('http://httpbin.org/get', { params: qs })
        .pipe(
          take(1),
          map(v => v.args)
        )
    )
  }
}

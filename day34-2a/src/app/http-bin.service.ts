import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom, map, take } from "rxjs";
import { UserData } from "./models";

@Injectable()
export class HttpBinService {

  constructor(private http: HttpClient) { }

  doPost(data: UserData): Promise<any> {
    return firstValueFrom(
      this.http.post<any>('http://httpbin.org/post', data)
    )
  }
  doPostAsForm(data: UserData) {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/x-www-form-urlencoded')
    let qs = new HttpParams()
        .set("userId", !!data.userId? data.userId: "")
        .set("name", data.name)
        .set("email", data.email)

      return firstValueFrom(
        this.http.post<any>('http://httpbin.org/post'
          , qs.toString()
          , { headers })
      )

  }
  doGet(data: UserData): Promise<UserData> {
    let qs = new HttpParams()
        .set("userId", !!data.userId? data.userId: "")
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

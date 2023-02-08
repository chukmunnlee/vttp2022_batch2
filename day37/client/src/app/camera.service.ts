import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom } from "rxjs";
import { UploadResult } from "./models";

@Injectable()
export class CameraService {

  imageData = ""

  constructor(private http: HttpClient) { }

  upload(form: any, image: Blob): Promise<UploadResult> {
    const formData = new FormData();
    formData.set("title", form['title'])
    formData.set("complain", form['complain'])
    formData.set("myImage", image)
    return firstValueFrom(
      this.http.post<UploadResult>('/upload', formData)
    )
  }

}

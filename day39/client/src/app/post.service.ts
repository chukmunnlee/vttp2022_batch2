import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {firstValueFrom} from "rxjs";
import {Post, PostResponse} from "./models";

@Injectable()
export class PostService {

	constructor(private http: HttpClient) { }

	postComment(post: Post): Promise<PostResponse> {

		const form = new FormData()
		form.set("email", post.email)
		form.set("title", post.title)
		form.set("text", post.text)
		form.set("image", post.image)

		return firstValueFrom(
			this.http.post<PostResponse>('/post', form)
		)

	}
}

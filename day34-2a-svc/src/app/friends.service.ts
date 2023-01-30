import { Injectable } from "@angular/core";
import { Subject } from "rxjs";
import { User } from "./models";

@Injectable()
export class FriendsService {

  friends = new Subject<User>()
}

import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, CanDeactivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import Dexie from "dexie";
import { Observable } from "rxjs";
import { ContactComponent } from "./components/contact.component";
import { CanLeave, Contact } from "./models";

@Injectable()
export class ContactRepository extends Dexie
    implements CanActivate, CanDeactivate<CanLeave> {

  // Variable to hold the table
  contact: Dexie.Table<Contact, string>

  canLogin = false

  constructor(private router: Router) {
    // Name of the database
    super('contactdb')
    this.version(1).stores({
      // contact table, with email as the PK
      contact: 'email'
    })

    this.contact = this.table('contact')
  }

  addContact(contact: Contact): Promise<string> {
    return this.contact.add(contact)
  }

  getAllContacts(): Promise<Contact[]> {
    return this.contact.orderBy('email').toArray()
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot)
      : boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    if (this.canLogin)
        return true
    return this.router.parseUrl('/')
  }

  canDeactivate(component: CanLeave, currentRoute: ActivatedRouteSnapshot, currentState: RouterStateSnapshot, nextState: RouterStateSnapshot)
      : boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    if (!component.canLeave())
      return true
    // true -> OK, false -> Cancel
    return prompt('Type YES to exit') == "YES"
    //return confirm('You have not save your data. Leave?')
  }

}


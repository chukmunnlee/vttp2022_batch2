import { NgModule } from "@angular/core";
import { MatToolbarModule } from '@angular/material/toolbar'
import { MatButtonModule } from '@angular/material/button'
import { MatIconModule } from '@angular/material/icon'
import { MatInputModule } from '@angular/material/input'
import { MatFormFieldModule } from '@angular/material/form-field'

const componets: any[] = [
  MatToolbarModule, MatButtonModule, MatIconModule,
  MatInputModule, MatFormFieldModule
]

@NgModule({
  imports: componets,
  exports: componets
})
export class MaterialModule { }


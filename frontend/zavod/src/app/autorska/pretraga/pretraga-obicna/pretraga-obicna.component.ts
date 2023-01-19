import { Component, OnInit } from '@angular/core';
import {FormControl} from '@angular/forms';
import {MatChipInputEvent} from '@angular/material/chips';

@Component({
  selector: 'app-pretraga-obicna',
  templateUrl: './pretraga-obicna.component.html',
  styleUrls: ['./pretraga-obicna.component.sass']
})
export class PretragaObicnaComponent {

  keywords = new Set(['angular', 'how-to', 'tutorial']);
  formControl = new FormControl([]);

  addKeywordFromInput(event: MatChipInputEvent) {
    if (event.value) {
      this.keywords.add(event.value);
      event.chipInput!.clear();
    }
  }

  removeKeyword(keyword: string) {
    this.keywords.delete(keyword);
  }

}

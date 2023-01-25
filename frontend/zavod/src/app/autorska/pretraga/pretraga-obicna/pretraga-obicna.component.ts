import { Component, OnInit } from '@angular/core';
import {FormControl} from '@angular/forms';
import {MatChipInputEvent} from '@angular/material/chips';
import { HttpRequestService } from 'src/services/util/http-request.service';
import { ResultService } from 'src/services/util/result.service';
var convert = require('xml-js');

@Component({
  selector: 'app-pretraga-obicna',
  templateUrl: './pretraga-obicna.component.html',
  styleUrls: ['./pretraga-obicna.component.sass']
})
export class PretragaObicnaComponent {

  keywords: Set<string> = new Set();
  formControl = new FormControl([]);

  constructor(private httpService: HttpRequestService, private resultService: ResultService) { }

  addKeywordFromInput(event: MatChipInputEvent) {
    if (event.value) {
      this.keywords.add(event.value);
      event.chipInput!.clear();
    }
  }

  removeKeyword(keyword: string) {
    this.keywords.delete(keyword);
  }

  search() {
    interface Parsed {
      search_request: {
        query: any[]
      }
    }
    let parsed: Parsed = {search_request: {query: []}};

    for (const condition of this.keywords) {
      parsed.search_request.query.push(condition);
    }


    const xml = convert.js2xml(parsed, {compact: true, spaces: 4});
    console.log(xml);
    
    
    this.httpService.post('http://localhost:8081/autorska/search', xml).subscribe((data: any) => {
        let options = {ignoreComment: true, alwaysChildren: true, compact: true, elementNameFn: this.parseName };
        let results = convert.xml2js(data, options);
        console.log(results);
        this.resultService.setResult(results);
    });
  }

  parseName(name: string) {
    let splitted = name.split(":");
    if(splitted.length > 1) {
      return splitted[1];
    }
    return splitted[0];
  }
    

}

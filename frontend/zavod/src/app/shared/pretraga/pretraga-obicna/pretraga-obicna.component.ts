import { Component, OnInit } from '@angular/core';
import {FormControl} from '@angular/forms';
import {MatChipInputEvent} from '@angular/material/chips';
import { Router } from '@angular/router';
import { AuthService } from 'src/services/auth/auth.service';
import { ParserService } from 'src/services/parser.service';
import { HttpRequestService, autorskaBackend, zigBackend } from 'src/services/util/http-request.service';
import { ResultService } from 'src/services/util/result.service';

@Component({
  selector: 'app-pretraga-obicna',
  templateUrl: './pretraga-obicna.component.html',
  styleUrls: ['./pretraga-obicna.component.sass']
})
export class PretragaObicnaComponent {

  keywords: Set<string> = new Set();
  formControl = new FormControl([]);
  serviceName: string = ""
  backend: string = ""

  constructor(
    private httpService: HttpRequestService, 
    private resultService: ResultService,
    private router: Router,
    private authService: AuthService,
    private parser: ParserService,
  ) { 
    this.serviceName = this.router.url.split("/")[1];
    if(this.serviceName == "autorska") {
      this.backend = autorskaBackend
    } else if(this.serviceName == "zig") {
      this.backend = zigBackend
    } 
  }

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

    const xml = this.parser.js2xml(parsed);
    console.log(xml);
    let url = this.backend + "/zahtevi/search"
    
    this.httpService.post(url, xml).subscribe((data: any) => {
        let results = this.parser.xml2js(data);
        console.log(results);
        this.resultService.setResult(results);
    });
  }

}

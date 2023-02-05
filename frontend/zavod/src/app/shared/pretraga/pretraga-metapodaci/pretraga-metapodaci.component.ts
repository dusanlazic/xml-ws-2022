import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ParserService } from 'src/services/parser.service';
import { HttpRequestService, autorskaBackend, zigBackend } from 'src/services/util/http-request.service';
import { ResultService } from 'src/services/util/result.service';

@Component({
  selector: 'app-pretraga-metapodaci',
  templateUrl: './pretraga-metapodaci.component.html',
  styleUrls: ['./pretraga-metapodaci.component.sass']
})
export class PretragaMetapodaciComponent implements OnInit {

  conditions: {name: string, value: string, operator: string, relation: string}[] = [];
  serviceName: string = "";
  backend: string = ""

  ops: string[] = [];
  constructor(
    private httpService: HttpRequestService, 
    private resultService: ResultService, 
    private router: Router,
    private parser: ParserService,
  ) {
    this.serviceName = this.router.url.split("/")[1];
    if(this.serviceName == "autorska") {
      this.backend = autorskaBackend
    } else if(this.serviceName == "zig") {
      this.backend = zigBackend
    } 
  }

  ngOnInit(): void {
    this.ops = ['I', 'ILI', 'NE'];
    this.conditions.push({name: 'Naziv', relation: '=', value: '', operator: 'I'});
  }

  addNewCondition() {
    this.conditions.push({name: '', relation: '=', value: '', operator: 'I'});
    
  }

  removeLastCondition() {
   this.conditions.splice(-1);
  }

  search() {
    interface Parsed {
      meta_search_request: {
        query: any[]
      }
    }
    let parsed: Parsed = {meta_search_request: {query: []}};

    for (const condition of this.conditions) {
      let obj = {predicate: condition.name, object: condition.value, relation: condition.relation, operator: condition.operator};
      parsed.meta_search_request.query.push(obj);
    }


    const xml = this.parser.js2xml(parsed);
    
    this.httpService.post(this.backend + '/zahtevi/search-meta', xml).subscribe((data: any) => {
        let results = this.parser.xml2js(data);
        this.resultService.setResult(results);
    });
    
  }

  trackByFn(i: number) {
    return i;
  }


}

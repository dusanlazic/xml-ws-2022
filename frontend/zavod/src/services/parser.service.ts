import { Injectable } from '@angular/core';
var convert = require('xml-js');

@Injectable({
  providedIn: 'root'
})
export class ParserService {

  js2xml(parsed: any) {
    return convert.js2xml(parsed, {compact: true, spaces: 4})
  }

  xml2js(data: any) {
    let options = {ignoreComment: true, alwaysChildren: true, compact: true, elementNameFn: this.parseName };
    let parsed = convert.xml2js(data, options);
    return parsed;
  }

  parseName(name: string) {
    let splitted = name.split(":");
    if(splitted.length > 1) {
      return splitted[1].toLowerCase();
    }
    return splitted[0].toLowerCase();
  }

  constructor() { }
}

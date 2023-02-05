import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpRequestService, autorskaBackend, zigBackend } from 'src/services/util/http-request.service';
import { saveAs } from 'file-saver';
import { ToastrService } from 'ngx-toastr';
import { HttpClient } from '@angular/common/http';
var _ = require('lodash');

@Component({
  selector: 'app-izvestaji',
  templateUrl: './izvestaji.component.html',
  styleUrls: ['./izvestaji.component.sass']
})
export class IzvestajiComponent implements OnInit {

  serviceName: string = ""

  range = new FormGroup({
    start: new FormControl<Date | null>(null),
    end: new FormControl<Date | null>(null),
  });

  constructor(private router: Router, private http: HttpClient, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.serviceName = this.router.url.split("/")[1];    
  }

  generisanje() {
    if(!this.range.value.start || !this.range.value.end) {
      this.toastr.error("Morate zadati opseg!");
      return;
    }
    let start = this.range.value.start!.toISOString().substring(0, 10)
    let end = this.range.value.end!.toISOString().substring(0, 10)
    let url = "";
    if (this.serviceName === "autorska") {
      url = autorskaBackend;
    } else {
      url = zigBackend;
    }

    this.http.get(url + "/zahtevi/izvestaj?startDate=" + start + "&endDate=" + end, {responseType: 'blob'}).subscribe((data: any) => {
      let blob = new Blob([data], {type: 'application/pdf'});
      saveAs(blob, "izvestaj.pdf");
    });
  }

}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/model/user';
import { AuthService } from 'src/services/auth/auth.service';
import { ParserService } from 'src/services/parser.service';
import { HttpRequestService, autorskaBackend, zigBackend } from 'src/services/util/http-request.service';

@Component({
  selector: 'app-resi',
  templateUrl: './resi.component.html',
  styleUrls: ['./resi.component.sass']
})
export class ResiComponent implements OnInit {


  status : boolean = true;
  loggedUser: User | undefined;
  obrazlozenje: string = "";

  date = new Date()
  dateStr = this.date.toISOString().split('T')[0]


  constructor(private authService: AuthService, 
    private httpService: HttpRequestService, 
    private router: Router, 
    private parser: ParserService,
    private toastr: ToastrService) {
    this.authService.getLoggedUser().subscribe((user: User | undefined) => {
      this.loggedUser = user;
      console.log(this.loggedUser);
      
    });
   }

  ngOnInit(): void {
    
  }

  podnesi() {
    let serviceName = this.router.url.split("/")[1];
    let brojZahteva = this.router.url.split("/")[3];
    let backend = "";

    if (serviceName == "autorska") {
      backend = autorskaBackend;
    } else if (serviceName == "zig") {
      backend = zigBackend;
    }

    let resenje = {
      Resenje: {
        odluka: {
          obrazlozenje: this.obrazlozenje,
          prihvacen: this.status,
        }
      }
    }

    let parsed = this.parser.js2xml(resenje);
    console.log(parsed);
    
    this.httpService.post(backend + "/resenja/" + brojZahteva, parsed).subscribe({
      next: (data) => {
        this.toastr.success("Uspešno ste podneli rešenje", "Uspešno");
      },
      error: (err) => {
        this.toastr.error("Došlo je do greške", "Greška");
      }
    });
  }

}

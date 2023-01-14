import { HttpClient, HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LocalStorageService } from './local-storage.service';

@Injectable({
    providedIn: 'root'
})

export class HttpRequestService {

    constructor(private httpClient: HttpClient, private localStorageService: LocalStorageService) {}

    createHeaders(): HttpHeaders {
        const headers = new HttpHeaders({
            'Content-type': 'application/xml',
        });

        return headers;
    }

    post(url: string, body: any) : Observable<any> {
        const headers = this.createHeaders();
        return this.httpClient.post(url, body, {headers, withCredentials: false}) 
    }

    get(url: string) : Observable<any> {
        const headers = this.createHeaders();
        return this.httpClient.get(url, {headers, withCredentials: false}) 
    }

    patch(url: string, body: any) : Observable<any> {
        const headers = this.createHeaders();
        return this.httpClient.patch(url, body, {headers, withCredentials: false}) 
    }
}

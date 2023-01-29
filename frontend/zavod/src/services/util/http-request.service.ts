import { HttpClient, HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LocalStorageService } from './local-storage.service';

export const autorskaBackend: string = "http://localhost:8081";
export const zigBackend: string = "http://localhost:8082";
export const korisinciBackend: string = "http://localhost:8083";


@Injectable({
    providedIn: 'root'
})

export class HttpRequestService {

    constructor(private httpClient: HttpClient, private localStorageService: LocalStorageService) {}

    createHeaders(): HttpHeaders {
        let token = this.localStorageService.get("access_token");
        let headers = new HttpHeaders({
            'Content-type': 'application/xml',
            'Accept': 'application/xml'
        });

        if (token) {
            console.log("appends");
            
            headers = headers.append("Authorization", "Bearer " + token)
        }
        console.log(this.localStorageService.get("access_token"));
        
        console.log(headers);
        

        return headers;
    }

    post(url: string, body: any) : Observable<any> {
        const headers = this.createHeaders();
        return this.httpClient.post(url, body, {headers, withCredentials: false, responseType: 'text'}) 
    }

    get(url: string) : Observable<any> {
        const headers = this.createHeaders();
        return this.httpClient.get(url, {headers, withCredentials: false, responseType: 'text'}) 
    }

    patch(url: string, body: any) : Observable<any> {
        const headers = this.createHeaders();
        return this.httpClient.patch(url, body, {headers, withCredentials: false}) 
    }
}

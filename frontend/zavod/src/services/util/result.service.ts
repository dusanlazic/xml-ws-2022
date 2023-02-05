import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
    providedIn: 'root'
})

export class ResultService {

	result: BehaviorSubject<any> = new BehaviorSubject<any>(null);

    constructor() {}

	setResult(result: any): void {
		this.result.next(result);
	}

	subscribeToResult(): any {
		return this.result.asObservable();
	}
  
}
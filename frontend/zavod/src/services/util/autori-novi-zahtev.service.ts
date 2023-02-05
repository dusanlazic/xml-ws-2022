import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
    providedIn: 'root'
})

export class AutoriNoviZahtevService {
  

	constructor() {}

	number: BehaviorSubject<number> = new BehaviorSubject<number>(0);

	step: BehaviorSubject<number> = new BehaviorSubject<number>(0);

	show: BehaviorSubject<boolean> = new BehaviorSubject(false);

	setStep(step: number) {
		this.step.next(step);
	}

	decreaseNumber(): void {
		if (this.number.value > 0) {
			this.number.next(this.number.value - 1);
		}
	}

	increaseNumber(): void {
		this.number.next(this.number.value + 1);
	}


	setButtons(show: boolean): void {
		this.show.next(show)
	}

}
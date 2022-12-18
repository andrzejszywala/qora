import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Trigger } from '../entity/trigger';


@Injectable()
export class TriggersResource {

    constructor(private http: HttpClient) { }

    triggers(user: string): Observable<Trigger[]> {
		let params = new HttpParams().set('user', user);
        return this.http.get<Trigger[]>('triggers', {params: params});
    }

}
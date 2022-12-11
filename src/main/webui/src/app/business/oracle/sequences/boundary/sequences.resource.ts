import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Sequence } from '../entity/sequence';


@Injectable()
export class SequencesResource {

    constructor(private http: HttpClient) { }

    sequences(user: string): Observable<Sequence[]> {
		let params = new HttpParams().set('user', user);
		
        return this.http.get<Sequence[]>('sequences', {params: params});
    }

}
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Procedure } from '../entity/procedure';


@Injectable()
export class ProceduresResource {

    constructor(private http: HttpClient) { }

    procedures(user: string): Observable<Procedure[]> {
		let params = new HttpParams().set('user', user);
        return this.http.get<Procedure[]>('procedures', {params: params});
    }

}
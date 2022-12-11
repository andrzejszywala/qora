import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Tables } from '../entity/tables';


@Injectable()
export class TablesResource {

    constructor(private http: HttpClient) { }

    tables(user: string): Observable<Tables[]> {
		let params = new HttpParams().set('user', user);
		
        return this.http.get<Tables[]>('tables', {params: params});
    }

}
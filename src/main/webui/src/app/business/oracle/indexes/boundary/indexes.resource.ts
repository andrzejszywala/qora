import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Index } from '../entity';


@Injectable()
export class IndexesResource {

    constructor(private http: HttpClient) { }

    indexes(user: string): Observable<Index[]> {
		let params = new HttpParams().set('user', user);
		
        return this.http.get<Index[]>('indexes', {params: params});
    }

}
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { View } from '../../views/entity/view';
import { Synonym } from '../entity/synonym';


@Injectable()
export class SynonymsResource {

    constructor(private http: HttpClient) { }

    synonyms(user: string): Observable<Synonym[]> {
        let params = new HttpParams().set('user', user);
		
        return this.http.get<Synonym[]>('synonyms', {params: params});
    }

}
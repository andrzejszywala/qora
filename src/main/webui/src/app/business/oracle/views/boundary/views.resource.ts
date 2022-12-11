import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { View } from '../entity/view';


@Injectable()
export class ViewsResource {

    constructor(private http: HttpClient) { }

    views(user: string): Observable<View[]> {
		let params = new HttpParams().set('user', user);
		
        return this.http.get<View[]>('views', {params: params});
    }

}
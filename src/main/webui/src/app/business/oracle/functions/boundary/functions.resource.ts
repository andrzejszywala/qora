import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Function } from '../entity/function';


@Injectable()
export class FunctionsResource {

    constructor(private http: HttpClient) { }

    functions(user: string): Observable<Function[]> {
		let params = new HttpParams().set('user', user);
        return this.http.get<Function[]>('functions', {params: params});
    }

}
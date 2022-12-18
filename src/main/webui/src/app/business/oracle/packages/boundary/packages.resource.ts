import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Package } from '../entity/package';


@Injectable()
export class PackagesResource {

    constructor(private http: HttpClient) { }

    packages(user: string): Observable<Package[]> {
		let params = new HttpParams().set('user', user);
        return this.http.get<Package[]>('packages', {params: params});
    }

}
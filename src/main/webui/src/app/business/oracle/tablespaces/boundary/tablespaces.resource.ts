import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Tablespace } from '../entity/tablespace';


@Injectable()
export class TablespacesResource {

    constructor(private http: HttpClient) { }

    tablespaces(): Observable<Tablespace[]> {
        return this.http.get<Tablespace[]>('tablespaces');
    }

}
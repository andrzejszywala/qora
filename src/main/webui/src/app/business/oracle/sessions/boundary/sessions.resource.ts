import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Session } from '../entity/session';


@Injectable()
export class SessionsResource {

    constructor(private http: HttpClient) { }

    sessions(): Observable<Session[]> {
        return this.http.get<Session[]>('sessions');
    }

}
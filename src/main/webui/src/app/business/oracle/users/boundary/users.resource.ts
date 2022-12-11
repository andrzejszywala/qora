import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../entity/user';


@Injectable()
export class UsersResource {

    constructor(private http: HttpClient) { }

    users(): Observable<User[]> {
        return this.http.get<User[]>('users');
    }

}
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Profile } from '../entity/profile';


@Injectable()
export class ProfilesResource {

    constructor(private http: HttpClient) { }

    profiles(): Observable<Profile[]> {
        return this.http.get<Profile[]>('profiles');
    }

}
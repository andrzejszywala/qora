import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Role } from '../entity/role';


@Injectable()
export class RolesResource {

    constructor(private http: HttpClient) { }

    roles(): Observable<Role[]> {
        return this.http.get<Role[]>('roles');
    }

}
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OracleFile } from '../entity/OracleFile';


@Injectable()
export class FilesResource {

    constructor(private http: HttpClient) { }

    files(): Observable<OracleFile[]> {
        return this.http.get<OracleFile[]>('files');
    }

}
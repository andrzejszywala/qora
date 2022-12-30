import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LogFile } from '../entity/log-file';
import { OracleFile } from '../entity/oracle-file';


@Injectable()
export class FilesResource {

    constructor(private http: HttpClient) { }

    files(): Observable<OracleFile[]> {
        return this.http.get<OracleFile[]>('files');
    }

	logFiles(): Observable<LogFile[]> {
        return this.http.get<LogFile[]>('files/log');
    }
}
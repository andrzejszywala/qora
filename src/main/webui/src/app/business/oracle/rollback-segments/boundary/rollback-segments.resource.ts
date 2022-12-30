import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RollbackSegment } from '../entity/rollback-segment';


@Injectable()
export class RollbackSegmentsResource {

    constructor(private http: HttpClient) { }

    rollbackSegments(): Observable<RollbackSegment[]> {
        return this.http.get<RollbackSegment[]>('rollback_segments');
    }

}
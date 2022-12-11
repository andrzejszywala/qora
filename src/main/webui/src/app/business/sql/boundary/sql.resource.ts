import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable } from 'rxjs';
import { CommandExecutionResult } from '../model/command-execution-result';
import { ExplainPlanRow } from '../model/explain-plan-row';


@Injectable()
export class SqlResource {

    constructor(private http: HttpClient) { }

    executeCommand(sqlCommand: string): Observable<CommandExecutionResult> {
        const headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');
        return this.http.post<CommandExecutionResult>('sql', sqlCommand, { headers, responseType: 'json'});
    }

    explainPlan(sqlCommand: string): Observable<ExplainPlanRow[]> {
        return this.http.post<ExplainPlanRow[]>('sql/explain_plan', sqlCommand);
    }
}
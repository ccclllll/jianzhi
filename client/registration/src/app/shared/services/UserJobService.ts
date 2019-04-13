import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { UrlConfig } from './url.config';
import {Job} from "../domain/Job";
const BASEURL = UrlConfig.BASEURL;

@Injectable()
export class UserJobService {
    constructor(private http: HttpClient) {
    }


    useJobs(userId: number): Observable<Job[]>{
        return this.http.get<Job[]>(`${BASEURL}/api/userjobs?userId=${userId}`);
    }
    jobUsers(jobId: number): Observable<Job[]>{
        return this.http.get<Job[]>(`${BASEURL}/api/userjobs?jobId=${jobId}`);
    }


}
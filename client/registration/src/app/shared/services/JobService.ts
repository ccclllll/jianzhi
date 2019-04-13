import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { UrlConfig } from './url.config';
import {Job} from "../domain/Job";
const BASEURL = UrlConfig.BASEURL;

@Injectable()
export class JobService {
    constructor(private http: HttpClient) {

    }

    userPost(userId: number): Observable<Job[]>{
        return this.http.get<Job[]>(`${BASEURL}/api/user_post?id=${userId}`);
    }

    addJob(job:Job){
        return this.http.post(`${BASEURL}/api/job`,job);
    }

    deleteJob(jobId: number){
        return this.http.delete(`${BASEURL}/api/job?jobId=${jobId}`);
    }
}
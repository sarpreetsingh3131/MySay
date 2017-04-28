import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class ApiService {

    constructor(private http: Http) { }

    getData(url) {
        return this.http.get(url).map((response: Response) => response.json());
    }
}
import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { Bien } from './bien.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class BienService {

    private resourceUrl = SERVER_API_URL + 'api/biens';

    constructor(private http: Http) { }

    create(bien: Bien): Observable<Bien> {
        const copy = this.convert(bien);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(bien: Bien): Observable<Bien> {
        const copy = this.convert(bien);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Bien> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to Bien.
     */
    private convertItemFromServer(json: any): Bien {
        const entity: Bien = Object.assign(new Bien(), json);
        return entity;
    }

    /**
     * Convert a Bien to a JSON which can be sent to the server.
     */
    private convert(bien: Bien): Bien {
        const copy: Bien = Object.assign({}, bien);
        return copy;
    }
}

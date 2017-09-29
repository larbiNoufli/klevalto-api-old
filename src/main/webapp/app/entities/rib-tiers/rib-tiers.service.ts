import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { RibTiers } from './rib-tiers.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class RibTiersService {

    private resourceUrl = SERVER_API_URL + 'api/rib-tiers';

    constructor(private http: Http) { }

    create(ribTiers: RibTiers): Observable<RibTiers> {
        const copy = this.convert(ribTiers);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(ribTiers: RibTiers): Observable<RibTiers> {
        const copy = this.convert(ribTiers);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<RibTiers> {
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
     * Convert a returned JSON object to RibTiers.
     */
    private convertItemFromServer(json: any): RibTiers {
        const entity: RibTiers = Object.assign(new RibTiers(), json);
        return entity;
    }

    /**
     * Convert a RibTiers to a JSON which can be sent to the server.
     */
    private convert(ribTiers: RibTiers): RibTiers {
        const copy: RibTiers = Object.assign({}, ribTiers);
        return copy;
    }
}

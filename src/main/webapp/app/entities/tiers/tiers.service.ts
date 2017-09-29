import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Tiers } from './tiers.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class TiersService {

    private resourceUrl = SERVER_API_URL + 'api/tiers';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(tiers: Tiers): Observable<Tiers> {
        const copy = this.convert(tiers);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(tiers: Tiers): Observable<Tiers> {
        const copy = this.convert(tiers);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<Tiers> {
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
     * Convert a returned JSON object to Tiers.
     */
    private convertItemFromServer(json: any): Tiers {
        const entity: Tiers = Object.assign(new Tiers(), json);
        entity.dateEmbauche = this.dateUtils
            .convertDateTimeFromServer(json.dateEmbauche);
        entity.dateDeNaissance = this.dateUtils
            .convertDateTimeFromServer(json.dateDeNaissance);
        entity.dateDeMariage = this.dateUtils
            .convertDateTimeFromServer(json.dateDeMariage);
        return entity;
    }

    /**
     * Convert a Tiers to a JSON which can be sent to the server.
     */
    private convert(tiers: Tiers): Tiers {
        const copy: Tiers = Object.assign({}, tiers);

        copy.dateEmbauche = this.dateUtils.toDate(tiers.dateEmbauche);

        copy.dateDeNaissance = this.dateUtils.toDate(tiers.dateDeNaissance);

        copy.dateDeMariage = this.dateUtils.toDate(tiers.dateDeMariage);
        return copy;
    }
}

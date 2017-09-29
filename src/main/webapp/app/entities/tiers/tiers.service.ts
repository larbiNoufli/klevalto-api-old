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
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(tiers: Tiers): Observable<Tiers> {
        const copy = this.convert(tiers);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<Tiers> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
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
        for (let i = 0; i < jsonResponse.length; i++) {
            this.convertItemFromServer(jsonResponse[i]);
        }
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convertItemFromServer(entity: any) {
        entity.dateEmbauche = this.dateUtils
            .convertDateTimeFromServer(entity.dateEmbauche);
        entity.dateDeNaissance = this.dateUtils
            .convertDateTimeFromServer(entity.dateDeNaissance);
        entity.dateDeMariage = this.dateUtils
            .convertDateTimeFromServer(entity.dateDeMariage);
    }

    private convert(tiers: Tiers): Tiers {
        const copy: Tiers = Object.assign({}, tiers);

        copy.dateEmbauche = this.dateUtils.toDate(tiers.dateEmbauche);

        copy.dateDeNaissance = this.dateUtils.toDate(tiers.dateDeNaissance);

        copy.dateDeMariage = this.dateUtils.toDate(tiers.dateDeMariage);
        return copy;
    }
}

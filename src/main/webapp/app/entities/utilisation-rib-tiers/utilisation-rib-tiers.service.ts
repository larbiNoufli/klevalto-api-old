import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { UtilisationRibTiers } from './utilisation-rib-tiers.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class UtilisationRibTiersService {

    private resourceUrl = SERVER_API_URL + 'api/utilisation-rib-tiers';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(utilisationRibTiers: UtilisationRibTiers): Observable<UtilisationRibTiers> {
        const copy = this.convert(utilisationRibTiers);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(utilisationRibTiers: UtilisationRibTiers): Observable<UtilisationRibTiers> {
        const copy = this.convert(utilisationRibTiers);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<UtilisationRibTiers> {
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
        entity.jourPrelevement = this.dateUtils
            .convertDateTimeFromServer(entity.jourPrelevement);
    }

    private convert(utilisationRibTiers: UtilisationRibTiers): UtilisationRibTiers {
        const copy: UtilisationRibTiers = Object.assign({}, utilisationRibTiers);

        copy.jourPrelevement = this.dateUtils.toDate(utilisationRibTiers.jourPrelevement);
        return copy;
    }
}

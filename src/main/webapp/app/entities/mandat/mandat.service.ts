import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Mandat } from './mandat.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class MandatService {

    private resourceUrl = SERVER_API_URL + 'api/mandats';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(mandat: Mandat): Observable<Mandat> {
        const copy = this.convert(mandat);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(mandat: Mandat): Observable<Mandat> {
        const copy = this.convert(mandat);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<Mandat> {
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
        entity.datefinjuridique = this.dateUtils
            .convertDateTimeFromServer(entity.datefinjuridique);
        entity.validationDate = this.dateUtils
            .convertDateTimeFromServer(entity.validationDate);
        entity.refusalDate = this.dateUtils
            .convertDateTimeFromServer(entity.refusalDate);
    }

    private convert(mandat: Mandat): Mandat {
        const copy: Mandat = Object.assign({}, mandat);

        copy.datefinjuridique = this.dateUtils.toDate(mandat.datefinjuridique);

        copy.validationDate = this.dateUtils.toDate(mandat.validationDate);

        copy.refusalDate = this.dateUtils.toDate(mandat.refusalDate);
        return copy;
    }
}

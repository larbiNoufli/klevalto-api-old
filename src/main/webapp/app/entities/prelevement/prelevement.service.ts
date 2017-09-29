import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Prelevement } from './prelevement.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class PrelevementService {

    private resourceUrl = SERVER_API_URL + 'api/prelevements';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(prelevement: Prelevement): Observable<Prelevement> {
        const copy = this.convert(prelevement);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(prelevement: Prelevement): Observable<Prelevement> {
        const copy = this.convert(prelevement);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<Prelevement> {
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
        entity.dateDuPrelevement = this.dateUtils
            .convertDateTimeFromServer(entity.dateDuPrelevement);
        entity.dateDetransfertVersMaya = this.dateUtils
            .convertDateTimeFromServer(entity.dateDetransfertVersMaya);
    }

    private convert(prelevement: Prelevement): Prelevement {
        const copy: Prelevement = Object.assign({}, prelevement);

        copy.dateDuPrelevement = this.dateUtils.toDate(prelevement.dateDuPrelevement);

        copy.dateDetransfertVersMaya = this.dateUtils.toDate(prelevement.dateDetransfertVersMaya);
        return copy;
    }
}

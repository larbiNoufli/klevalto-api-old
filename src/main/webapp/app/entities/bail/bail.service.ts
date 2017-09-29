import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Bail } from './bail.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class BailService {

    private resourceUrl = SERVER_API_URL + 'api/bails';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(bail: Bail): Observable<Bail> {
        const copy = this.convert(bail);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(bail: Bail): Observable<Bail> {
        const copy = this.convert(bail);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<Bail> {
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
        entity.dateCreation = this.dateUtils
            .convertDateTimeFromServer(entity.dateCreation);
        entity.dateDetransfertVersMaya = this.dateUtils
            .convertDateTimeFromServer(entity.dateDetransfertVersMaya);
    }

    private convert(bail: Bail): Bail {
        const copy: Bail = Object.assign({}, bail);

        copy.dateCreation = this.dateUtils.toDate(bail.dateCreation);

        copy.dateDetransfertVersMaya = this.dateUtils.toDate(bail.dateDetransfertVersMaya);
        return copy;
    }
}

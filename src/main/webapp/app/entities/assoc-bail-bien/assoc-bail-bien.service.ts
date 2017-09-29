import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { AssocBailBien } from './assoc-bail-bien.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class AssocBailBienService {

    private resourceUrl = SERVER_API_URL + 'api/assoc-bail-biens';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(assocBailBien: AssocBailBien): Observable<AssocBailBien> {
        const copy = this.convert(assocBailBien);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(assocBailBien: AssocBailBien): Observable<AssocBailBien> {
        const copy = this.convert(assocBailBien);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<AssocBailBien> {
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
        entity.datedebutjuridique = this.dateUtils
            .convertDateTimeFromServer(entity.datedebutjuridique);
        entity.datefinjuridique = this.dateUtils
            .convertDateTimeFromServer(entity.datefinjuridique);
        entity.validationDate = this.dateUtils
            .convertDateTimeFromServer(entity.validationDate);
        entity.refusalDate = this.dateUtils
            .convertDateTimeFromServer(entity.refusalDate);
    }

    private convert(assocBailBien: AssocBailBien): AssocBailBien {
        const copy: AssocBailBien = Object.assign({}, assocBailBien);

        copy.datedebutjuridique = this.dateUtils.toDate(assocBailBien.datedebutjuridique);

        copy.datefinjuridique = this.dateUtils.toDate(assocBailBien.datefinjuridique);

        copy.validationDate = this.dateUtils.toDate(assocBailBien.validationDate);

        copy.refusalDate = this.dateUtils.toDate(assocBailBien.refusalDate);
        return copy;
    }
}

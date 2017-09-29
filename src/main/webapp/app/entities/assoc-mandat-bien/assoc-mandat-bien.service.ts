import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { AssocMandatBien } from './assoc-mandat-bien.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class AssocMandatBienService {

    private resourceUrl = SERVER_API_URL + 'api/assoc-mandat-biens';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(assocMandatBien: AssocMandatBien): Observable<AssocMandatBien> {
        const copy = this.convert(assocMandatBien);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(assocMandatBien: AssocMandatBien): Observable<AssocMandatBien> {
        const copy = this.convert(assocMandatBien);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<AssocMandatBien> {
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
     * Convert a returned JSON object to AssocMandatBien.
     */
    private convertItemFromServer(json: any): AssocMandatBien {
        const entity: AssocMandatBien = Object.assign(new AssocMandatBien(), json);
        entity.datedebutjuridique = this.dateUtils
            .convertDateTimeFromServer(json.datedebutjuridique);
        entity.datefinjuridique = this.dateUtils
            .convertDateTimeFromServer(json.datefinjuridique);
        entity.validationDate = this.dateUtils
            .convertDateTimeFromServer(json.validationDate);
        entity.refusalDate = this.dateUtils
            .convertDateTimeFromServer(json.refusalDate);
        return entity;
    }

    /**
     * Convert a AssocMandatBien to a JSON which can be sent to the server.
     */
    private convert(assocMandatBien: AssocMandatBien): AssocMandatBien {
        const copy: AssocMandatBien = Object.assign({}, assocMandatBien);

        copy.datedebutjuridique = this.dateUtils.toDate(assocMandatBien.datedebutjuridique);

        copy.datefinjuridique = this.dateUtils.toDate(assocMandatBien.datefinjuridique);

        copy.validationDate = this.dateUtils.toDate(assocMandatBien.validationDate);

        copy.refusalDate = this.dateUtils.toDate(assocMandatBien.refusalDate);
        return copy;
    }
}

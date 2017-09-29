import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { AdressePostale } from './adresse-postale.model';
import { AdressePostaleService } from './adresse-postale.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-adresse-postale',
    templateUrl: './adresse-postale.component.html'
})
export class AdressePostaleComponent implements OnInit, OnDestroy {
adressePostales: AdressePostale[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private adressePostaleService: AdressePostaleService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.adressePostaleService.query().subscribe(
            (res: ResponseWrapper) => {
                this.adressePostales = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInAdressePostales();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: AdressePostale) {
        return item.id;
    }
    registerChangeInAdressePostales() {
        this.eventSubscriber = this.eventManager.subscribe('adressePostaleListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

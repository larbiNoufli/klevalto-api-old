import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { AssocMandatBien } from './assoc-mandat-bien.model';
import { AssocMandatBienService } from './assoc-mandat-bien.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-assoc-mandat-bien',
    templateUrl: './assoc-mandat-bien.component.html'
})
export class AssocMandatBienComponent implements OnInit, OnDestroy {
assocMandatBiens: AssocMandatBien[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private assocMandatBienService: AssocMandatBienService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.assocMandatBienService.query().subscribe(
            (res: ResponseWrapper) => {
                this.assocMandatBiens = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInAssocMandatBiens();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: AssocMandatBien) {
        return item.id;
    }
    registerChangeInAssocMandatBiens() {
        this.eventSubscriber = this.eventManager.subscribe('assocMandatBienListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { Mandat } from './mandat.model';
import { MandatService } from './mandat.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-mandat',
    templateUrl: './mandat.component.html'
})
export class MandatComponent implements OnInit, OnDestroy {
mandats: Mandat[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private mandatService: MandatService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.mandatService.query().subscribe(
            (res: ResponseWrapper) => {
                this.mandats = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInMandats();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Mandat) {
        return item.id;
    }
    registerChangeInMandats() {
        this.eventSubscriber = this.eventManager.subscribe('mandatListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}

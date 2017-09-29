import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { Prelevement } from './prelevement.model';
import { PrelevementService } from './prelevement.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-prelevement',
    templateUrl: './prelevement.component.html'
})
export class PrelevementComponent implements OnInit, OnDestroy {
prelevements: Prelevement[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private prelevementService: PrelevementService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.prelevementService.query().subscribe(
            (res: ResponseWrapper) => {
                this.prelevements = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInPrelevements();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Prelevement) {
        return item.id;
    }
    registerChangeInPrelevements() {
        this.eventSubscriber = this.eventManager.subscribe('prelevementListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}

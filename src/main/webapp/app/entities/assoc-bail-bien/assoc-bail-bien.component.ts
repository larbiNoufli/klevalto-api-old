import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { AssocBailBien } from './assoc-bail-bien.model';
import { AssocBailBienService } from './assoc-bail-bien.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-assoc-bail-bien',
    templateUrl: './assoc-bail-bien.component.html'
})
export class AssocBailBienComponent implements OnInit, OnDestroy {
assocBailBiens: AssocBailBien[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private assocBailBienService: AssocBailBienService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.assocBailBienService.query().subscribe(
            (res: ResponseWrapper) => {
                this.assocBailBiens = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInAssocBailBiens();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: AssocBailBien) {
        return item.id;
    }
    registerChangeInAssocBailBiens() {
        this.eventSubscriber = this.eventManager.subscribe('assocBailBienListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}

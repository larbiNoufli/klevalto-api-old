import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { LocataireBien } from './locataire-bien.model';
import { LocataireBienService } from './locataire-bien.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-locataire-bien',
    templateUrl: './locataire-bien.component.html'
})
export class LocataireBienComponent implements OnInit, OnDestroy {
locataireBiens: LocataireBien[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private locataireBienService: LocataireBienService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.locataireBienService.query().subscribe(
            (res: ResponseWrapper) => {
                this.locataireBiens = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInLocataireBiens();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: LocataireBien) {
        return item.id;
    }
    registerChangeInLocataireBiens() {
        this.eventSubscriber = this.eventManager.subscribe('locataireBienListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { RibTiers } from './rib-tiers.model';
import { RibTiersService } from './rib-tiers.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-rib-tiers',
    templateUrl: './rib-tiers.component.html'
})
export class RibTiersComponent implements OnInit, OnDestroy {
ribTiers: RibTiers[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private ribTiersService: RibTiersService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.ribTiersService.query().subscribe(
            (res: ResponseWrapper) => {
                this.ribTiers = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInRibTiers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: RibTiers) {
        return item.id;
    }
    registerChangeInRibTiers() {
        this.eventSubscriber = this.eventManager.subscribe('ribTiersListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

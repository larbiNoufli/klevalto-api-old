import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { UtilisationRibTiers } from './utilisation-rib-tiers.model';
import { UtilisationRibTiersService } from './utilisation-rib-tiers.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-utilisation-rib-tiers',
    templateUrl: './utilisation-rib-tiers.component.html'
})
export class UtilisationRibTiersComponent implements OnInit, OnDestroy {
utilisationRibTiers: UtilisationRibTiers[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private utilisationRibTiersService: UtilisationRibTiersService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.utilisationRibTiersService.query().subscribe(
            (res: ResponseWrapper) => {
                this.utilisationRibTiers = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInUtilisationRibTiers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: UtilisationRibTiers) {
        return item.id;
    }
    registerChangeInUtilisationRibTiers() {
        this.eventSubscriber = this.eventManager.subscribe('utilisationRibTiersListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

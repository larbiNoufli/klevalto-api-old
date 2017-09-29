import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { TypeBien } from './type-bien.model';
import { TypeBienService } from './type-bien.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-type-bien',
    templateUrl: './type-bien.component.html'
})
export class TypeBienComponent implements OnInit, OnDestroy {
typeBiens: TypeBien[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private typeBienService: TypeBienService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.typeBienService.query().subscribe(
            (res: ResponseWrapper) => {
                this.typeBiens = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInTypeBiens();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: TypeBien) {
        return item.id;
    }
    registerChangeInTypeBiens() {
        this.eventSubscriber = this.eventManager.subscribe('typeBienListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}

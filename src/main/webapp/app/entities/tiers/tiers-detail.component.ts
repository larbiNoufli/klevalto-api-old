import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Tiers } from './tiers.model';
import { TiersService } from './tiers.service';

@Component({
    selector: 'jhi-tiers-detail',
    templateUrl: './tiers-detail.component.html'
})
export class TiersDetailComponent implements OnInit, OnDestroy {

    tiers: Tiers;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private tiersService: TiersService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTiers();
    }

    load(id) {
        this.tiersService.find(id).subscribe((tiers) => {
            this.tiers = tiers;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTiers() {
        this.eventSubscriber = this.eventManager.subscribe(
            'tiersListModification',
            (response) => this.load(this.tiers.id)
        );
    }
}

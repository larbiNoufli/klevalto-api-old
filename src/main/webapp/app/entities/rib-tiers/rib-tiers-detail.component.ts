import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { RibTiers } from './rib-tiers.model';
import { RibTiersService } from './rib-tiers.service';

@Component({
    selector: 'jhi-rib-tiers-detail',
    templateUrl: './rib-tiers-detail.component.html'
})
export class RibTiersDetailComponent implements OnInit, OnDestroy {

    ribTiers: RibTiers;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private ribTiersService: RibTiersService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInRibTiers();
    }

    load(id) {
        this.ribTiersService.find(id).subscribe((ribTiers) => {
            this.ribTiers = ribTiers;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInRibTiers() {
        this.eventSubscriber = this.eventManager.subscribe(
            'ribTiersListModification',
            (response) => this.load(this.ribTiers.id)
        );
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { UtilisationRibTiers } from './utilisation-rib-tiers.model';
import { UtilisationRibTiersService } from './utilisation-rib-tiers.service';

@Component({
    selector: 'jhi-utilisation-rib-tiers-detail',
    templateUrl: './utilisation-rib-tiers-detail.component.html'
})
export class UtilisationRibTiersDetailComponent implements OnInit, OnDestroy {

    utilisationRibTiers: UtilisationRibTiers;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private utilisationRibTiersService: UtilisationRibTiersService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInUtilisationRibTiers();
    }

    load(id) {
        this.utilisationRibTiersService.find(id).subscribe((utilisationRibTiers) => {
            this.utilisationRibTiers = utilisationRibTiers;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInUtilisationRibTiers() {
        this.eventSubscriber = this.eventManager.subscribe(
            'utilisationRibTiersListModification',
            (response) => this.load(this.utilisationRibTiers.id)
        );
    }
}

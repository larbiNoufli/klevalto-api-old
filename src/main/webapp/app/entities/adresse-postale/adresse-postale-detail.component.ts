import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { AdressePostale } from './adresse-postale.model';
import { AdressePostaleService } from './adresse-postale.service';

@Component({
    selector: 'jhi-adresse-postale-detail',
    templateUrl: './adresse-postale-detail.component.html'
})
export class AdressePostaleDetailComponent implements OnInit, OnDestroy {

    adressePostale: AdressePostale;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private adressePostaleService: AdressePostaleService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAdressePostales();
    }

    load(id) {
        this.adressePostaleService.find(id).subscribe((adressePostale) => {
            this.adressePostale = adressePostale;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAdressePostales() {
        this.eventSubscriber = this.eventManager.subscribe(
            'adressePostaleListModification',
            (response) => this.load(this.adressePostale.id)
        );
    }
}

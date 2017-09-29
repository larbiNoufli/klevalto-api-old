import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { LocataireBien } from './locataire-bien.model';
import { LocataireBienService } from './locataire-bien.service';

@Component({
    selector: 'jhi-locataire-bien-detail',
    templateUrl: './locataire-bien-detail.component.html'
})
export class LocataireBienDetailComponent implements OnInit, OnDestroy {

    locataireBien: LocataireBien;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private locataireBienService: LocataireBienService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInLocataireBiens();
    }

    load(id) {
        this.locataireBienService.find(id).subscribe((locataireBien) => {
            this.locataireBien = locataireBien;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInLocataireBiens() {
        this.eventSubscriber = this.eventManager.subscribe(
            'locataireBienListModification',
            (response) => this.load(this.locataireBien.id)
        );
    }
}

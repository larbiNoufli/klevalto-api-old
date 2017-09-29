import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Prelevement } from './prelevement.model';
import { PrelevementService } from './prelevement.service';

@Component({
    selector: 'jhi-prelevement-detail',
    templateUrl: './prelevement-detail.component.html'
})
export class PrelevementDetailComponent implements OnInit, OnDestroy {

    prelevement: Prelevement;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private prelevementService: PrelevementService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPrelevements();
    }

    load(id) {
        this.prelevementService.find(id).subscribe((prelevement) => {
            this.prelevement = prelevement;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPrelevements() {
        this.eventSubscriber = this.eventManager.subscribe(
            'prelevementListModification',
            (response) => this.load(this.prelevement.id)
        );
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Bail } from './bail.model';
import { BailService } from './bail.service';

@Component({
    selector: 'jhi-bail-detail',
    templateUrl: './bail-detail.component.html'
})
export class BailDetailComponent implements OnInit, OnDestroy {

    bail: Bail;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private bailService: BailService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInBails();
    }

    load(id) {
        this.bailService.find(id).subscribe((bail) => {
            this.bail = bail;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInBails() {
        this.eventSubscriber = this.eventManager.subscribe(
            'bailListModification',
            (response) => this.load(this.bail.id)
        );
    }
}

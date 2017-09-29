import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Pole } from './pole.model';
import { PoleService } from './pole.service';

@Component({
    selector: 'jhi-pole-detail',
    templateUrl: './pole-detail.component.html'
})
export class PoleDetailComponent implements OnInit, OnDestroy {

    pole: Pole;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private poleService: PoleService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInPoles();
    }

    load(id) {
        this.poleService.find(id).subscribe((pole) => {
            this.pole = pole;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInPoles() {
        this.eventSubscriber = this.eventManager.subscribe(
            'poleListModification',
            (response) => this.load(this.pole.id)
        );
    }
}

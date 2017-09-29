import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Bien } from './bien.model';
import { BienService } from './bien.service';

@Component({
    selector: 'jhi-bien-detail',
    templateUrl: './bien-detail.component.html'
})
export class BienDetailComponent implements OnInit, OnDestroy {

    bien: Bien;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private bienService: BienService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInBiens();
    }

    load(id) {
        this.bienService.find(id).subscribe((bien) => {
            this.bien = bien;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInBiens() {
        this.eventSubscriber = this.eventManager.subscribe(
            'bienListModification',
            (response) => this.load(this.bien.id)
        );
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { AssocBailBien } from './assoc-bail-bien.model';
import { AssocBailBienService } from './assoc-bail-bien.service';

@Component({
    selector: 'jhi-assoc-bail-bien-detail',
    templateUrl: './assoc-bail-bien-detail.component.html'
})
export class AssocBailBienDetailComponent implements OnInit, OnDestroy {

    assocBailBien: AssocBailBien;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private assocBailBienService: AssocBailBienService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAssocBailBiens();
    }

    load(id) {
        this.assocBailBienService.find(id).subscribe((assocBailBien) => {
            this.assocBailBien = assocBailBien;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAssocBailBiens() {
        this.eventSubscriber = this.eventManager.subscribe(
            'assocBailBienListModification',
            (response) => this.load(this.assocBailBien.id)
        );
    }
}

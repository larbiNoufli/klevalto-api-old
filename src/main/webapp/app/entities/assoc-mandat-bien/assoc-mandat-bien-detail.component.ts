import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { AssocMandatBien } from './assoc-mandat-bien.model';
import { AssocMandatBienService } from './assoc-mandat-bien.service';

@Component({
    selector: 'jhi-assoc-mandat-bien-detail',
    templateUrl: './assoc-mandat-bien-detail.component.html'
})
export class AssocMandatBienDetailComponent implements OnInit, OnDestroy {

    assocMandatBien: AssocMandatBien;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private assocMandatBienService: AssocMandatBienService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAssocMandatBiens();
    }

    load(id) {
        this.assocMandatBienService.find(id).subscribe((assocMandatBien) => {
            this.assocMandatBien = assocMandatBien;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAssocMandatBiens() {
        this.eventSubscriber = this.eventManager.subscribe(
            'assocMandatBienListModification',
            (response) => this.load(this.assocMandatBien.id)
        );
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { TypeBien } from './type-bien.model';
import { TypeBienService } from './type-bien.service';

@Component({
    selector: 'jhi-type-bien-detail',
    templateUrl: './type-bien-detail.component.html'
})
export class TypeBienDetailComponent implements OnInit, OnDestroy {

    typeBien: TypeBien;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private typeBienService: TypeBienService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTypeBiens();
    }

    load(id) {
        this.typeBienService.find(id).subscribe((typeBien) => {
            this.typeBien = typeBien;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTypeBiens() {
        this.eventSubscriber = this.eventManager.subscribe(
            'typeBienListModification',
            (response) => this.load(this.typeBien.id)
        );
    }
}

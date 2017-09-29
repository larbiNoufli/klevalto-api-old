import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Mandat } from './mandat.model';
import { MandatService } from './mandat.service';

@Component({
    selector: 'jhi-mandat-detail',
    templateUrl: './mandat-detail.component.html'
})
export class MandatDetailComponent implements OnInit, OnDestroy {

    mandat: Mandat;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private mandatService: MandatService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInMandats();
    }

    load(id) {
        this.mandatService.find(id).subscribe((mandat) => {
            this.mandat = mandat;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInMandats() {
        this.eventSubscriber = this.eventManager.subscribe(
            'mandatListModification',
            (response) => this.load(this.mandat.id)
        );
    }
}

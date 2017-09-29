import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Tiers } from './tiers.model';
import { TiersPopupService } from './tiers-popup.service';
import { TiersService } from './tiers.service';

@Component({
    selector: 'jhi-tiers-delete-dialog',
    templateUrl: './tiers-delete-dialog.component.html'
})
export class TiersDeleteDialogComponent {

    tiers: Tiers;

    constructor(
        private tiersService: TiersService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tiersService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'tiersListModification',
                content: 'Deleted an tiers'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tiers-delete-popup',
    template: ''
})
export class TiersDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tiersPopupService: TiersPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.tiersPopupService
                .open(TiersDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

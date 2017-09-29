import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { RibTiers } from './rib-tiers.model';
import { RibTiersPopupService } from './rib-tiers-popup.service';
import { RibTiersService } from './rib-tiers.service';

@Component({
    selector: 'jhi-rib-tiers-delete-dialog',
    templateUrl: './rib-tiers-delete-dialog.component.html'
})
export class RibTiersDeleteDialogComponent {

    ribTiers: RibTiers;

    constructor(
        private ribTiersService: RibTiersService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.ribTiersService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'ribTiersListModification',
                content: 'Deleted an ribTiers'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-rib-tiers-delete-popup',
    template: ''
})
export class RibTiersDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private ribTiersPopupService: RibTiersPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.ribTiersPopupService
                .open(RibTiersDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

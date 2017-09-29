import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { UtilisationRibTiers } from './utilisation-rib-tiers.model';
import { UtilisationRibTiersPopupService } from './utilisation-rib-tiers-popup.service';
import { UtilisationRibTiersService } from './utilisation-rib-tiers.service';

@Component({
    selector: 'jhi-utilisation-rib-tiers-delete-dialog',
    templateUrl: './utilisation-rib-tiers-delete-dialog.component.html'
})
export class UtilisationRibTiersDeleteDialogComponent {

    utilisationRibTiers: UtilisationRibTiers;

    constructor(
        private utilisationRibTiersService: UtilisationRibTiersService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.utilisationRibTiersService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'utilisationRibTiersListModification',
                content: 'Deleted an utilisationRibTiers'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-utilisation-rib-tiers-delete-popup',
    template: ''
})
export class UtilisationRibTiersDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private utilisationRibTiersPopupService: UtilisationRibTiersPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.utilisationRibTiersPopupService
                .open(UtilisationRibTiersDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

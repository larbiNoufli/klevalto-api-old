import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AdressePostale } from './adresse-postale.model';
import { AdressePostalePopupService } from './adresse-postale-popup.service';
import { AdressePostaleService } from './adresse-postale.service';

@Component({
    selector: 'jhi-adresse-postale-delete-dialog',
    templateUrl: './adresse-postale-delete-dialog.component.html'
})
export class AdressePostaleDeleteDialogComponent {

    adressePostale: AdressePostale;

    constructor(
        private adressePostaleService: AdressePostaleService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.adressePostaleService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'adressePostaleListModification',
                content: 'Deleted an adressePostale'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-adresse-postale-delete-popup',
    template: ''
})
export class AdressePostaleDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private adressePostalePopupService: AdressePostalePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.adressePostalePopupService
                .open(AdressePostaleDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { LocataireBien } from './locataire-bien.model';
import { LocataireBienPopupService } from './locataire-bien-popup.service';
import { LocataireBienService } from './locataire-bien.service';

@Component({
    selector: 'jhi-locataire-bien-delete-dialog',
    templateUrl: './locataire-bien-delete-dialog.component.html'
})
export class LocataireBienDeleteDialogComponent {

    locataireBien: LocataireBien;

    constructor(
        private locataireBienService: LocataireBienService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.locataireBienService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'locataireBienListModification',
                content: 'Deleted an locataireBien'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-locataire-bien-delete-popup',
    template: ''
})
export class LocataireBienDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private locataireBienPopupService: LocataireBienPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.locataireBienPopupService
                .open(LocataireBienDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

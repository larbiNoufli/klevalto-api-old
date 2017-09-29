import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Prelevement } from './prelevement.model';
import { PrelevementPopupService } from './prelevement-popup.service';
import { PrelevementService } from './prelevement.service';

@Component({
    selector: 'jhi-prelevement-delete-dialog',
    templateUrl: './prelevement-delete-dialog.component.html'
})
export class PrelevementDeleteDialogComponent {

    prelevement: Prelevement;

    constructor(
        private prelevementService: PrelevementService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.prelevementService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'prelevementListModification',
                content: 'Deleted an prelevement'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-prelevement-delete-popup',
    template: ''
})
export class PrelevementDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private prelevementPopupService: PrelevementPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.prelevementPopupService
                .open(PrelevementDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Bail } from './bail.model';
import { BailPopupService } from './bail-popup.service';
import { BailService } from './bail.service';

@Component({
    selector: 'jhi-bail-delete-dialog',
    templateUrl: './bail-delete-dialog.component.html'
})
export class BailDeleteDialogComponent {

    bail: Bail;

    constructor(
        private bailService: BailService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.bailService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'bailListModification',
                content: 'Deleted an bail'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-bail-delete-popup',
    template: ''
})
export class BailDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private bailPopupService: BailPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.bailPopupService
                .open(BailDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

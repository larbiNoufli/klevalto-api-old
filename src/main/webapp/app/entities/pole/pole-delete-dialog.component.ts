import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Pole } from './pole.model';
import { PolePopupService } from './pole-popup.service';
import { PoleService } from './pole.service';

@Component({
    selector: 'jhi-pole-delete-dialog',
    templateUrl: './pole-delete-dialog.component.html'
})
export class PoleDeleteDialogComponent {

    pole: Pole;

    constructor(
        private poleService: PoleService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.poleService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'poleListModification',
                content: 'Deleted an pole'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-pole-delete-popup',
    template: ''
})
export class PoleDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private polePopupService: PolePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.polePopupService
                .open(PoleDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

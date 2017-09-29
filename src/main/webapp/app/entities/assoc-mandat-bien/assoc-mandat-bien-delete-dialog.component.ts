import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AssocMandatBien } from './assoc-mandat-bien.model';
import { AssocMandatBienPopupService } from './assoc-mandat-bien-popup.service';
import { AssocMandatBienService } from './assoc-mandat-bien.service';

@Component({
    selector: 'jhi-assoc-mandat-bien-delete-dialog',
    templateUrl: './assoc-mandat-bien-delete-dialog.component.html'
})
export class AssocMandatBienDeleteDialogComponent {

    assocMandatBien: AssocMandatBien;

    constructor(
        private assocMandatBienService: AssocMandatBienService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.assocMandatBienService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'assocMandatBienListModification',
                content: 'Deleted an assocMandatBien'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-assoc-mandat-bien-delete-popup',
    template: ''
})
export class AssocMandatBienDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private assocMandatBienPopupService: AssocMandatBienPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.assocMandatBienPopupService
                .open(AssocMandatBienDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

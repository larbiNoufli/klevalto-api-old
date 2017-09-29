import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AssocBailBien } from './assoc-bail-bien.model';
import { AssocBailBienPopupService } from './assoc-bail-bien-popup.service';
import { AssocBailBienService } from './assoc-bail-bien.service';

@Component({
    selector: 'jhi-assoc-bail-bien-delete-dialog',
    templateUrl: './assoc-bail-bien-delete-dialog.component.html'
})
export class AssocBailBienDeleteDialogComponent {

    assocBailBien: AssocBailBien;

    constructor(
        private assocBailBienService: AssocBailBienService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.assocBailBienService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'assocBailBienListModification',
                content: 'Deleted an assocBailBien'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-assoc-bail-bien-delete-popup',
    template: ''
})
export class AssocBailBienDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private assocBailBienPopupService: AssocBailBienPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.assocBailBienPopupService
                .open(AssocBailBienDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

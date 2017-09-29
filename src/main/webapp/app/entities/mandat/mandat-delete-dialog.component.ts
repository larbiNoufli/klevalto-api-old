import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Mandat } from './mandat.model';
import { MandatPopupService } from './mandat-popup.service';
import { MandatService } from './mandat.service';

@Component({
    selector: 'jhi-mandat-delete-dialog',
    templateUrl: './mandat-delete-dialog.component.html'
})
export class MandatDeleteDialogComponent {

    mandat: Mandat;

    constructor(
        private mandatService: MandatService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.mandatService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'mandatListModification',
                content: 'Deleted an mandat'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-mandat-delete-popup',
    template: ''
})
export class MandatDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private mandatPopupService: MandatPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.mandatPopupService
                .open(MandatDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

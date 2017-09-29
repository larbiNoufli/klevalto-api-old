import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Mandat } from './mandat.model';
import { MandatPopupService } from './mandat-popup.service';
import { MandatService } from './mandat.service';

@Component({
    selector: 'jhi-mandat-dialog',
    templateUrl: './mandat-dialog.component.html'
})
export class MandatDialogComponent implements OnInit {

    mandat: Mandat;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private mandatService: MandatService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.mandat.id !== undefined) {
            this.subscribeToSaveResponse(
                this.mandatService.update(this.mandat));
        } else {
            this.subscribeToSaveResponse(
                this.mandatService.create(this.mandat));
        }
    }

    private subscribeToSaveResponse(result: Observable<Mandat>) {
        result.subscribe((res: Mandat) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Mandat) {
        this.eventManager.broadcast({ name: 'mandatListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-mandat-popup',
    template: ''
})
export class MandatPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private mandatPopupService: MandatPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.mandatPopupService
                    .open(MandatDialogComponent as Component, params['id']);
            } else {
                this.mandatPopupService
                    .open(MandatDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

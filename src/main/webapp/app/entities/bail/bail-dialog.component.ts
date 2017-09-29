import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Bail } from './bail.model';
import { BailPopupService } from './bail-popup.service';
import { BailService } from './bail.service';

@Component({
    selector: 'jhi-bail-dialog',
    templateUrl: './bail-dialog.component.html'
})
export class BailDialogComponent implements OnInit {

    bail: Bail;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private bailService: BailService,
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
        if (this.bail.id !== undefined) {
            this.subscribeToSaveResponse(
                this.bailService.update(this.bail));
        } else {
            this.subscribeToSaveResponse(
                this.bailService.create(this.bail));
        }
    }

    private subscribeToSaveResponse(result: Observable<Bail>) {
        result.subscribe((res: Bail) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Bail) {
        this.eventManager.broadcast({ name: 'bailListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-bail-popup',
    template: ''
})
export class BailPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private bailPopupService: BailPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.bailPopupService
                    .open(BailDialogComponent as Component, params['id']);
            } else {
                this.bailPopupService
                    .open(BailDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

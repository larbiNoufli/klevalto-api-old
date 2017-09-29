import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Pole } from './pole.model';
import { PolePopupService } from './pole-popup.service';
import { PoleService } from './pole.service';

@Component({
    selector: 'jhi-pole-dialog',
    templateUrl: './pole-dialog.component.html'
})
export class PoleDialogComponent implements OnInit {

    pole: Pole;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private poleService: PoleService,
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
        if (this.pole.id !== undefined) {
            this.subscribeToSaveResponse(
                this.poleService.update(this.pole));
        } else {
            this.subscribeToSaveResponse(
                this.poleService.create(this.pole));
        }
    }

    private subscribeToSaveResponse(result: Observable<Pole>) {
        result.subscribe((res: Pole) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Pole) {
        this.eventManager.broadcast({ name: 'poleListModification', content: 'OK'});
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
    selector: 'jhi-pole-popup',
    template: ''
})
export class PolePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private polePopupService: PolePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.polePopupService
                    .open(PoleDialogComponent as Component, params['id']);
            } else {
                this.polePopupService
                    .open(PoleDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

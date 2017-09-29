import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Prelevement } from './prelevement.model';
import { PrelevementPopupService } from './prelevement-popup.service';
import { PrelevementService } from './prelevement.service';

@Component({
    selector: 'jhi-prelevement-dialog',
    templateUrl: './prelevement-dialog.component.html'
})
export class PrelevementDialogComponent implements OnInit {

    prelevement: Prelevement;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private prelevementService: PrelevementService,
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
        if (this.prelevement.id !== undefined) {
            this.subscribeToSaveResponse(
                this.prelevementService.update(this.prelevement));
        } else {
            this.subscribeToSaveResponse(
                this.prelevementService.create(this.prelevement));
        }
    }

    private subscribeToSaveResponse(result: Observable<Prelevement>) {
        result.subscribe((res: Prelevement) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Prelevement) {
        this.eventManager.broadcast({ name: 'prelevementListModification', content: 'OK'});
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
    selector: 'jhi-prelevement-popup',
    template: ''
})
export class PrelevementPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private prelevementPopupService: PrelevementPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.prelevementPopupService
                    .open(PrelevementDialogComponent as Component, params['id']);
            } else {
                this.prelevementPopupService
                    .open(PrelevementDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

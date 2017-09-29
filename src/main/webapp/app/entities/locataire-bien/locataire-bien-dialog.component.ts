import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { LocataireBien } from './locataire-bien.model';
import { LocataireBienPopupService } from './locataire-bien-popup.service';
import { LocataireBienService } from './locataire-bien.service';

@Component({
    selector: 'jhi-locataire-bien-dialog',
    templateUrl: './locataire-bien-dialog.component.html'
})
export class LocataireBienDialogComponent implements OnInit {

    locataireBien: LocataireBien;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private locataireBienService: LocataireBienService,
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
        if (this.locataireBien.id !== undefined) {
            this.subscribeToSaveResponse(
                this.locataireBienService.update(this.locataireBien));
        } else {
            this.subscribeToSaveResponse(
                this.locataireBienService.create(this.locataireBien));
        }
    }

    private subscribeToSaveResponse(result: Observable<LocataireBien>) {
        result.subscribe((res: LocataireBien) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: LocataireBien) {
        this.eventManager.broadcast({ name: 'locataireBienListModification', content: 'OK'});
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
    selector: 'jhi-locataire-bien-popup',
    template: ''
})
export class LocataireBienPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private locataireBienPopupService: LocataireBienPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.locataireBienPopupService
                    .open(LocataireBienDialogComponent as Component, params['id']);
            } else {
                this.locataireBienPopupService
                    .open(LocataireBienDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

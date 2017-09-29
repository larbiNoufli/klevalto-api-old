import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { AdressePostale } from './adresse-postale.model';
import { AdressePostalePopupService } from './adresse-postale-popup.service';
import { AdressePostaleService } from './adresse-postale.service';

@Component({
    selector: 'jhi-adresse-postale-dialog',
    templateUrl: './adresse-postale-dialog.component.html'
})
export class AdressePostaleDialogComponent implements OnInit {

    adressePostale: AdressePostale;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private adressePostaleService: AdressePostaleService,
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
        if (this.adressePostale.id !== undefined) {
            this.subscribeToSaveResponse(
                this.adressePostaleService.update(this.adressePostale));
        } else {
            this.subscribeToSaveResponse(
                this.adressePostaleService.create(this.adressePostale));
        }
    }

    private subscribeToSaveResponse(result: Observable<AdressePostale>) {
        result.subscribe((res: AdressePostale) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: AdressePostale) {
        this.eventManager.broadcast({ name: 'adressePostaleListModification', content: 'OK'});
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
    selector: 'jhi-adresse-postale-popup',
    template: ''
})
export class AdressePostalePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private adressePostalePopupService: AdressePostalePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.adressePostalePopupService
                    .open(AdressePostaleDialogComponent as Component, params['id']);
            } else {
                this.adressePostalePopupService
                    .open(AdressePostaleDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

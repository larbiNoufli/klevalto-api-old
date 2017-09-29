import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { AssocMandatBien } from './assoc-mandat-bien.model';
import { AssocMandatBienPopupService } from './assoc-mandat-bien-popup.service';
import { AssocMandatBienService } from './assoc-mandat-bien.service';
import { Tiers, TiersService } from '../tiers';
import { Mandat, MandatService } from '../mandat';
import { Bien, BienService } from '../bien';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-assoc-mandat-bien-dialog',
    templateUrl: './assoc-mandat-bien-dialog.component.html'
})
export class AssocMandatBienDialogComponent implements OnInit {

    assocMandatBien: AssocMandatBien;
    isSaving: boolean;

    tiers: Tiers[];

    mandats: Mandat[];

    biens: Bien[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private assocMandatBienService: AssocMandatBienService,
        private tiersService: TiersService,
        private mandatService: MandatService,
        private bienService: BienService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.tiersService.query()
            .subscribe((res: ResponseWrapper) => { this.tiers = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.mandatService.query()
            .subscribe((res: ResponseWrapper) => { this.mandats = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.bienService.query()
            .subscribe((res: ResponseWrapper) => { this.biens = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.assocMandatBien.id !== undefined) {
            this.subscribeToSaveResponse(
                this.assocMandatBienService.update(this.assocMandatBien));
        } else {
            this.subscribeToSaveResponse(
                this.assocMandatBienService.create(this.assocMandatBien));
        }
    }

    private subscribeToSaveResponse(result: Observable<AssocMandatBien>) {
        result.subscribe((res: AssocMandatBien) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: AssocMandatBien) {
        this.eventManager.broadcast({ name: 'assocMandatBienListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }

    trackTiersById(index: number, item: Tiers) {
        return item.id;
    }

    trackMandatById(index: number, item: Mandat) {
        return item.id;
    }

    trackBienById(index: number, item: Bien) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-assoc-mandat-bien-popup',
    template: ''
})
export class AssocMandatBienPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private assocMandatBienPopupService: AssocMandatBienPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.assocMandatBienPopupService
                    .open(AssocMandatBienDialogComponent as Component, params['id']);
            } else {
                this.assocMandatBienPopupService
                    .open(AssocMandatBienDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { AssocBailBien } from './assoc-bail-bien.model';
import { AssocBailBienPopupService } from './assoc-bail-bien-popup.service';
import { AssocBailBienService } from './assoc-bail-bien.service';
import { Tiers, TiersService } from '../tiers';
import { Bail, BailService } from '../bail';
import { Bien, BienService } from '../bien';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-assoc-bail-bien-dialog',
    templateUrl: './assoc-bail-bien-dialog.component.html'
})
export class AssocBailBienDialogComponent implements OnInit {

    assocBailBien: AssocBailBien;
    isSaving: boolean;

    tiers: Tiers[];

    bails: Bail[];

    biens: Bien[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private assocBailBienService: AssocBailBienService,
        private tiersService: TiersService,
        private bailService: BailService,
        private bienService: BienService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.tiersService.query()
            .subscribe((res: ResponseWrapper) => { this.tiers = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.bailService.query()
            .subscribe((res: ResponseWrapper) => { this.bails = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.bienService.query()
            .subscribe((res: ResponseWrapper) => { this.biens = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.assocBailBien.id !== undefined) {
            this.subscribeToSaveResponse(
                this.assocBailBienService.update(this.assocBailBien));
        } else {
            this.subscribeToSaveResponse(
                this.assocBailBienService.create(this.assocBailBien));
        }
    }

    private subscribeToSaveResponse(result: Observable<AssocBailBien>) {
        result.subscribe((res: AssocBailBien) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: AssocBailBien) {
        this.eventManager.broadcast({ name: 'assocBailBienListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackTiersById(index: number, item: Tiers) {
        return item.id;
    }

    trackBailById(index: number, item: Bail) {
        return item.id;
    }

    trackBienById(index: number, item: Bien) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-assoc-bail-bien-popup',
    template: ''
})
export class AssocBailBienPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private assocBailBienPopupService: AssocBailBienPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.assocBailBienPopupService
                    .open(AssocBailBienDialogComponent as Component, params['id']);
            } else {
                this.assocBailBienPopupService
                    .open(AssocBailBienDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

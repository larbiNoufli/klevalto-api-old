import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Bien } from './bien.model';
import { BienPopupService } from './bien-popup.service';
import { BienService } from './bien.service';
import { TypeBien, TypeBienService } from '../type-bien';
import { Mandat, MandatService } from '../mandat';
import { AdressePostale, AdressePostaleService } from '../adresse-postale';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-bien-dialog',
    templateUrl: './bien-dialog.component.html'
})
export class BienDialogComponent implements OnInit {

    bien: Bien;
    isSaving: boolean;

    typebiens: TypeBien[];

    mandats: Mandat[];

    adressepostales: AdressePostale[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private bienService: BienService,
        private typeBienService: TypeBienService,
        private mandatService: MandatService,
        private adressePostaleService: AdressePostaleService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.typeBienService
            .query({filter: 'bien-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.bien.typeBien || !this.bien.typeBien.id) {
                    this.typebiens = res.json;
                } else {
                    this.typeBienService
                        .find(this.bien.typeBien.id)
                        .subscribe((subRes: TypeBien) => {
                            this.typebiens = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.mandatService
            .query({filter: 'bien-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.bien.mandat || !this.bien.mandat.id) {
                    this.mandats = res.json;
                } else {
                    this.mandatService
                        .find(this.bien.mandat.id)
                        .subscribe((subRes: Mandat) => {
                            this.mandats = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.adressePostaleService
            .query({filter: 'bien-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.bien.adressePostale || !this.bien.adressePostale.id) {
                    this.adressepostales = res.json;
                } else {
                    this.adressePostaleService
                        .find(this.bien.adressePostale.id)
                        .subscribe((subRes: AdressePostale) => {
                            this.adressepostales = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.bien.id !== undefined) {
            this.subscribeToSaveResponse(
                this.bienService.update(this.bien));
        } else {
            this.subscribeToSaveResponse(
                this.bienService.create(this.bien));
        }
    }

    private subscribeToSaveResponse(result: Observable<Bien>) {
        result.subscribe((res: Bien) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Bien) {
        this.eventManager.broadcast({ name: 'bienListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }

    trackTypeBienById(index: number, item: TypeBien) {
        return item.id;
    }

    trackMandatById(index: number, item: Mandat) {
        return item.id;
    }

    trackAdressePostaleById(index: number, item: AdressePostale) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-bien-popup',
    template: ''
})
export class BienPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private bienPopupService: BienPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.bienPopupService
                    .open(BienDialogComponent as Component, params['id']);
            } else {
                this.bienPopupService
                    .open(BienDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

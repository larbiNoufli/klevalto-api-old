import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Tiers } from './tiers.model';
import { TiersPopupService } from './tiers-popup.service';
import { TiersService } from './tiers.service';
import { AdressePostale, AdressePostaleService } from '../adresse-postale';
import { Profil, ProfilService } from '../profil';
import { UtilisationRibTiers, UtilisationRibTiersService } from '../utilisation-rib-tiers';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-tiers-dialog',
    templateUrl: './tiers-dialog.component.html'
})
export class TiersDialogComponent implements OnInit {

    tiers: Tiers;
    isSaving: boolean;

    adressepostales: AdressePostale[];

    profils: Profil[];

    utilisationribtiers: UtilisationRibTiers[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private tiersService: TiersService,
        private adressePostaleService: AdressePostaleService,
        private profilService: ProfilService,
        private utilisationRibTiersService: UtilisationRibTiersService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.adressePostaleService.query()
            .subscribe((res: ResponseWrapper) => { this.adressepostales = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.profilService.query()
            .subscribe((res: ResponseWrapper) => { this.profils = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.utilisationRibTiersService.query()
            .subscribe((res: ResponseWrapper) => { this.utilisationribtiers = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.tiers.id !== undefined) {
            this.subscribeToSaveResponse(
                this.tiersService.update(this.tiers));
        } else {
            this.subscribeToSaveResponse(
                this.tiersService.create(this.tiers));
        }
    }

    private subscribeToSaveResponse(result: Observable<Tiers>) {
        result.subscribe((res: Tiers) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Tiers) {
        this.eventManager.broadcast({ name: 'tiersListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackAdressePostaleById(index: number, item: AdressePostale) {
        return item.id;
    }

    trackProfilById(index: number, item: Profil) {
        return item.id;
    }

    trackUtilisationRibTiersById(index: number, item: UtilisationRibTiers) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-tiers-popup',
    template: ''
})
export class TiersPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tiersPopupService: TiersPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.tiersPopupService
                    .open(TiersDialogComponent as Component, params['id']);
            } else {
                this.tiersPopupService
                    .open(TiersDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

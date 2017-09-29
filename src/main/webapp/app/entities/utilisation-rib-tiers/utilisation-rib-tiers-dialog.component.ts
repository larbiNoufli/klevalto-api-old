import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { UtilisationRibTiers } from './utilisation-rib-tiers.model';
import { UtilisationRibTiersPopupService } from './utilisation-rib-tiers-popup.service';
import { UtilisationRibTiersService } from './utilisation-rib-tiers.service';
import { Prelevement, PrelevementService } from '../prelevement';
import { RibTiers, RibTiersService } from '../rib-tiers';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-utilisation-rib-tiers-dialog',
    templateUrl: './utilisation-rib-tiers-dialog.component.html'
})
export class UtilisationRibTiersDialogComponent implements OnInit {

    utilisationRibTiers: UtilisationRibTiers;
    isSaving: boolean;

    prelevements: Prelevement[];

    ribtiers: RibTiers[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private utilisationRibTiersService: UtilisationRibTiersService,
        private prelevementService: PrelevementService,
        private ribTiersService: RibTiersService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.prelevementService.query()
            .subscribe((res: ResponseWrapper) => { this.prelevements = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.ribTiersService.query()
            .subscribe((res: ResponseWrapper) => { this.ribtiers = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.utilisationRibTiers.id !== undefined) {
            this.subscribeToSaveResponse(
                this.utilisationRibTiersService.update(this.utilisationRibTiers));
        } else {
            this.subscribeToSaveResponse(
                this.utilisationRibTiersService.create(this.utilisationRibTiers));
        }
    }

    private subscribeToSaveResponse(result: Observable<UtilisationRibTiers>) {
        result.subscribe((res: UtilisationRibTiers) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: UtilisationRibTiers) {
        this.eventManager.broadcast({ name: 'utilisationRibTiersListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }

    trackPrelevementById(index: number, item: Prelevement) {
        return item.id;
    }

    trackRibTiersById(index: number, item: RibTiers) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-utilisation-rib-tiers-popup',
    template: ''
})
export class UtilisationRibTiersPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private utilisationRibTiersPopupService: UtilisationRibTiersPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.utilisationRibTiersPopupService
                    .open(UtilisationRibTiersDialogComponent as Component, params['id']);
            } else {
                this.utilisationRibTiersPopupService
                    .open(UtilisationRibTiersDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { RibTiers } from './rib-tiers.model';
import { RibTiersPopupService } from './rib-tiers-popup.service';
import { RibTiersService } from './rib-tiers.service';

@Component({
    selector: 'jhi-rib-tiers-dialog',
    templateUrl: './rib-tiers-dialog.component.html'
})
export class RibTiersDialogComponent implements OnInit {

    ribTiers: RibTiers;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private ribTiersService: RibTiersService,
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
        if (this.ribTiers.id !== undefined) {
            this.subscribeToSaveResponse(
                this.ribTiersService.update(this.ribTiers));
        } else {
            this.subscribeToSaveResponse(
                this.ribTiersService.create(this.ribTiers));
        }
    }

    private subscribeToSaveResponse(result: Observable<RibTiers>) {
        result.subscribe((res: RibTiers) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: RibTiers) {
        this.eventManager.broadcast({ name: 'ribTiersListModification', content: 'OK'});
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
    selector: 'jhi-rib-tiers-popup',
    template: ''
})
export class RibTiersPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private ribTiersPopupService: RibTiersPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.ribTiersPopupService
                    .open(RibTiersDialogComponent as Component, params['id']);
            } else {
                this.ribTiersPopupService
                    .open(RibTiersDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

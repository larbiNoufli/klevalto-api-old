import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { TypeBien } from './type-bien.model';
import { TypeBienPopupService } from './type-bien-popup.service';
import { TypeBienService } from './type-bien.service';

@Component({
    selector: 'jhi-type-bien-dialog',
    templateUrl: './type-bien-dialog.component.html'
})
export class TypeBienDialogComponent implements OnInit {

    typeBien: TypeBien;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private typeBienService: TypeBienService,
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
        if (this.typeBien.id !== undefined) {
            this.subscribeToSaveResponse(
                this.typeBienService.update(this.typeBien));
        } else {
            this.subscribeToSaveResponse(
                this.typeBienService.create(this.typeBien));
        }
    }

    private subscribeToSaveResponse(result: Observable<TypeBien>) {
        result.subscribe((res: TypeBien) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: TypeBien) {
        this.eventManager.broadcast({ name: 'typeBienListModification', content: 'OK'});
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
    selector: 'jhi-type-bien-popup',
    template: ''
})
export class TypeBienPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private typeBienPopupService: TypeBienPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.typeBienPopupService
                    .open(TypeBienDialogComponent as Component, params['id']);
            } else {
                this.typeBienPopupService
                    .open(TypeBienDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Profil } from './profil.model';
import { ProfilPopupService } from './profil-popup.service';
import { ProfilService } from './profil.service';
import { Pole, PoleService } from '../pole';
import { Role, RoleService } from '../role';
import { Bien, BienService } from '../bien';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-profil-dialog',
    templateUrl: './profil-dialog.component.html'
})
export class ProfilDialogComponent implements OnInit {

    profil: Profil;
    isSaving: boolean;

    profils: Pole[];

    roles: Role[];

    biens: Bien[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private profilService: ProfilService,
        private poleService: PoleService,
        private roleService: RoleService,
        private bienService: BienService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.poleService
            .query({filter: 'profil-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.profil.profil || !this.profil.profil.id) {
                    this.profils = res.json;
                } else {
                    this.poleService
                        .find(this.profil.profil.id)
                        .subscribe((subRes: Pole) => {
                            this.profils = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.roleService.query()
            .subscribe((res: ResponseWrapper) => { this.roles = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.bienService.query()
            .subscribe((res: ResponseWrapper) => { this.biens = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.profil.id !== undefined) {
            this.subscribeToSaveResponse(
                this.profilService.update(this.profil));
        } else {
            this.subscribeToSaveResponse(
                this.profilService.create(this.profil));
        }
    }

    private subscribeToSaveResponse(result: Observable<Profil>) {
        result.subscribe((res: Profil) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Profil) {
        this.eventManager.broadcast({ name: 'profilListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }

    trackPoleById(index: number, item: Pole) {
        return item.id;
    }

    trackRoleById(index: number, item: Role) {
        return item.id;
    }

    trackBienById(index: number, item: Bien) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-profil-popup',
    template: ''
})
export class ProfilPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private profilPopupService: ProfilPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.profilPopupService
                    .open(ProfilDialogComponent as Component, params['id']);
            } else {
                this.profilPopupService
                    .open(ProfilDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

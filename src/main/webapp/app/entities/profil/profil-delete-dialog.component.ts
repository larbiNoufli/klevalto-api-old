import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Profil } from './profil.model';
import { ProfilPopupService } from './profil-popup.service';
import { ProfilService } from './profil.service';

@Component({
    selector: 'jhi-profil-delete-dialog',
    templateUrl: './profil-delete-dialog.component.html'
})
export class ProfilDeleteDialogComponent {

    profil: Profil;

    constructor(
        private profilService: ProfilService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.profilService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'profilListModification',
                content: 'Deleted an profil'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-profil-delete-popup',
    template: ''
})
export class ProfilDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private profilPopupService: ProfilPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.profilPopupService
                .open(ProfilDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}

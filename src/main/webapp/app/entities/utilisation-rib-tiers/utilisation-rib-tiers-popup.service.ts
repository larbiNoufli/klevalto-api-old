import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { UtilisationRibTiers } from './utilisation-rib-tiers.model';
import { UtilisationRibTiersService } from './utilisation-rib-tiers.service';

@Injectable()
export class UtilisationRibTiersPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private utilisationRibTiersService: UtilisationRibTiersService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.utilisationRibTiersService.find(id).subscribe((utilisationRibTiers) => {
                    utilisationRibTiers.jourPrelevement = this.datePipe
                        .transform(utilisationRibTiers.jourPrelevement, 'yyyy-MM-ddTHH:mm:ss');
                    this.ngbModalRef = this.utilisationRibTiersModalRef(component, utilisationRibTiers);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.utilisationRibTiersModalRef(component, new UtilisationRibTiers());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    utilisationRibTiersModalRef(component: Component, utilisationRibTiers: UtilisationRibTiers): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.utilisationRibTiers = utilisationRibTiers;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}

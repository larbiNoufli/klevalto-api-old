import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { Tiers } from './tiers.model';
import { TiersService } from './tiers.service';

@Injectable()
export class TiersPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private tiersService: TiersService

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
                this.tiersService.find(id).subscribe((tiers) => {
                    tiers.dateEmbauche = this.datePipe
                        .transform(tiers.dateEmbauche, 'yyyy-MM-ddTHH:mm:ss');
                    tiers.dateDeNaissance = this.datePipe
                        .transform(tiers.dateDeNaissance, 'yyyy-MM-ddTHH:mm:ss');
                    tiers.dateDeMariage = this.datePipe
                        .transform(tiers.dateDeMariage, 'yyyy-MM-ddTHH:mm:ss');
                    this.ngbModalRef = this.tiersModalRef(component, tiers);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.tiersModalRef(component, new Tiers());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    tiersModalRef(component: Component, tiers: Tiers): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.tiers = tiers;
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

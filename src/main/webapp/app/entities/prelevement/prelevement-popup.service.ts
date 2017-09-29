import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { Prelevement } from './prelevement.model';
import { PrelevementService } from './prelevement.service';

@Injectable()
export class PrelevementPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private prelevementService: PrelevementService

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
                this.prelevementService.find(id).subscribe((prelevement) => {
                    prelevement.dateDuPrelevement = this.datePipe
                        .transform(prelevement.dateDuPrelevement, 'yyyy-MM-ddTHH:mm:ss');
                    prelevement.dateDetransfertVersMaya = this.datePipe
                        .transform(prelevement.dateDetransfertVersMaya, 'yyyy-MM-ddTHH:mm:ss');
                    this.ngbModalRef = this.prelevementModalRef(component, prelevement);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.prelevementModalRef(component, new Prelevement());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    prelevementModalRef(component: Component, prelevement: Prelevement): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.prelevement = prelevement;
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

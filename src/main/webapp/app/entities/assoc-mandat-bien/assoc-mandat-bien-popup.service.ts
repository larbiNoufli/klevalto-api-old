import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { AssocMandatBien } from './assoc-mandat-bien.model';
import { AssocMandatBienService } from './assoc-mandat-bien.service';

@Injectable()
export class AssocMandatBienPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private assocMandatBienService: AssocMandatBienService

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
                this.assocMandatBienService.find(id).subscribe((assocMandatBien) => {
                    assocMandatBien.datedebutjuridique = this.datePipe
                        .transform(assocMandatBien.datedebutjuridique, 'yyyy-MM-ddTHH:mm:ss');
                    assocMandatBien.datefinjuridique = this.datePipe
                        .transform(assocMandatBien.datefinjuridique, 'yyyy-MM-ddTHH:mm:ss');
                    assocMandatBien.validationDate = this.datePipe
                        .transform(assocMandatBien.validationDate, 'yyyy-MM-ddTHH:mm:ss');
                    assocMandatBien.refusalDate = this.datePipe
                        .transform(assocMandatBien.refusalDate, 'yyyy-MM-ddTHH:mm:ss');
                    this.ngbModalRef = this.assocMandatBienModalRef(component, assocMandatBien);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.assocMandatBienModalRef(component, new AssocMandatBien());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    assocMandatBienModalRef(component: Component, assocMandatBien: AssocMandatBien): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.assocMandatBien = assocMandatBien;
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

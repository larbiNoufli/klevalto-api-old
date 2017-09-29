import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { AssocBailBien } from './assoc-bail-bien.model';
import { AssocBailBienService } from './assoc-bail-bien.service';

@Injectable()
export class AssocBailBienPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private assocBailBienService: AssocBailBienService

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
                this.assocBailBienService.find(id).subscribe((assocBailBien) => {
                    assocBailBien.datedebutjuridique = this.datePipe
                        .transform(assocBailBien.datedebutjuridique, 'yyyy-MM-ddTHH:mm:ss');
                    assocBailBien.datefinjuridique = this.datePipe
                        .transform(assocBailBien.datefinjuridique, 'yyyy-MM-ddTHH:mm:ss');
                    assocBailBien.validationDate = this.datePipe
                        .transform(assocBailBien.validationDate, 'yyyy-MM-ddTHH:mm:ss');
                    assocBailBien.refusalDate = this.datePipe
                        .transform(assocBailBien.refusalDate, 'yyyy-MM-ddTHH:mm:ss');
                    this.ngbModalRef = this.assocBailBienModalRef(component, assocBailBien);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.assocBailBienModalRef(component, new AssocBailBien());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    assocBailBienModalRef(component: Component, assocBailBien: AssocBailBien): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.assocBailBien = assocBailBien;
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

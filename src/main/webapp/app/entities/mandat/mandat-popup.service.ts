import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { Mandat } from './mandat.model';
import { MandatService } from './mandat.service';

@Injectable()
export class MandatPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private mandatService: MandatService

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
                this.mandatService.find(id).subscribe((mandat) => {
                    mandat.datefinjuridique = this.datePipe
                        .transform(mandat.datefinjuridique, 'yyyy-MM-ddTHH:mm:ss');
                    mandat.validationDate = this.datePipe
                        .transform(mandat.validationDate, 'yyyy-MM-ddTHH:mm:ss');
                    mandat.refusalDate = this.datePipe
                        .transform(mandat.refusalDate, 'yyyy-MM-ddTHH:mm:ss');
                    this.ngbModalRef = this.mandatModalRef(component, mandat);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.mandatModalRef(component, new Mandat());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    mandatModalRef(component: Component, mandat: Mandat): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.mandat = mandat;
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

import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DatePipe } from '@angular/common';
import { Bail } from './bail.model';
import { BailService } from './bail.service';

@Injectable()
export class BailPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private bailService: BailService

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
                this.bailService.find(id).subscribe((bail) => {
                    bail.dateCreation = this.datePipe
                        .transform(bail.dateCreation, 'yyyy-MM-ddTHH:mm:ss');
                    bail.dateDetransfertVersMaya = this.datePipe
                        .transform(bail.dateDetransfertVersMaya, 'yyyy-MM-ddTHH:mm:ss');
                    this.ngbModalRef = this.bailModalRef(component, bail);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.bailModalRef(component, new Bail());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    bailModalRef(component: Component, bail: Bail): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.bail = bail;
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

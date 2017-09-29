import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { AssocMandatBienComponent } from './assoc-mandat-bien.component';
import { AssocMandatBienDetailComponent } from './assoc-mandat-bien-detail.component';
import { AssocMandatBienPopupComponent } from './assoc-mandat-bien-dialog.component';
import { AssocMandatBienDeletePopupComponent } from './assoc-mandat-bien-delete-dialog.component';

export const assocMandatBienRoute: Routes = [
    {
        path: 'assoc-mandat-bien',
        component: AssocMandatBienComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.assocMandatBien.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'assoc-mandat-bien/:id',
        component: AssocMandatBienDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.assocMandatBien.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const assocMandatBienPopupRoute: Routes = [
    {
        path: 'assoc-mandat-bien-new',
        component: AssocMandatBienPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.assocMandatBien.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'assoc-mandat-bien/:id/edit',
        component: AssocMandatBienPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.assocMandatBien.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'assoc-mandat-bien/:id/delete',
        component: AssocMandatBienDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.assocMandatBien.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { MandatComponent } from './mandat.component';
import { MandatDetailComponent } from './mandat-detail.component';
import { MandatPopupComponent } from './mandat-dialog.component';
import { MandatDeletePopupComponent } from './mandat-delete-dialog.component';

export const mandatRoute: Routes = [
    {
        path: 'mandat',
        component: MandatComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.mandat.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'mandat/:id',
        component: MandatDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.mandat.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const mandatPopupRoute: Routes = [
    {
        path: 'mandat-new',
        component: MandatPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.mandat.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'mandat/:id/edit',
        component: MandatPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.mandat.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'mandat/:id/delete',
        component: MandatDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.mandat.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

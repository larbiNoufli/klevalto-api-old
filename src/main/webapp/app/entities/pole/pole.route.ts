import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { PoleComponent } from './pole.component';
import { PoleDetailComponent } from './pole-detail.component';
import { PolePopupComponent } from './pole-dialog.component';
import { PoleDeletePopupComponent } from './pole-delete-dialog.component';

export const poleRoute: Routes = [
    {
        path: 'pole',
        component: PoleComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.pole.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'pole/:id',
        component: PoleDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.pole.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const polePopupRoute: Routes = [
    {
        path: 'pole-new',
        component: PolePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.pole.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'pole/:id/edit',
        component: PolePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.pole.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'pole/:id/delete',
        component: PoleDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.pole.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { TiersComponent } from './tiers.component';
import { TiersDetailComponent } from './tiers-detail.component';
import { TiersPopupComponent } from './tiers-dialog.component';
import { TiersDeletePopupComponent } from './tiers-delete-dialog.component';

export const tiersRoute: Routes = [
    {
        path: 'tiers',
        component: TiersComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.tiers.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'tiers/:id',
        component: TiersDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.tiers.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tiersPopupRoute: Routes = [
    {
        path: 'tiers-new',
        component: TiersPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.tiers.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tiers/:id/edit',
        component: TiersPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.tiers.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tiers/:id/delete',
        component: TiersDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.tiers.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

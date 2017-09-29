import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { RibTiersComponent } from './rib-tiers.component';
import { RibTiersDetailComponent } from './rib-tiers-detail.component';
import { RibTiersPopupComponent } from './rib-tiers-dialog.component';
import { RibTiersDeletePopupComponent } from './rib-tiers-delete-dialog.component';

export const ribTiersRoute: Routes = [
    {
        path: 'rib-tiers',
        component: RibTiersComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.ribTiers.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'rib-tiers/:id',
        component: RibTiersDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.ribTiers.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const ribTiersPopupRoute: Routes = [
    {
        path: 'rib-tiers-new',
        component: RibTiersPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.ribTiers.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'rib-tiers/:id/edit',
        component: RibTiersPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.ribTiers.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'rib-tiers/:id/delete',
        component: RibTiersDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.ribTiers.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

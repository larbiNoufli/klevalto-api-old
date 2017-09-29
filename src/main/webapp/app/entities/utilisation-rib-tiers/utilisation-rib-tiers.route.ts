import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UtilisationRibTiersComponent } from './utilisation-rib-tiers.component';
import { UtilisationRibTiersDetailComponent } from './utilisation-rib-tiers-detail.component';
import { UtilisationRibTiersPopupComponent } from './utilisation-rib-tiers-dialog.component';
import { UtilisationRibTiersDeletePopupComponent } from './utilisation-rib-tiers-delete-dialog.component';

export const utilisationRibTiersRoute: Routes = [
    {
        path: 'utilisation-rib-tiers',
        component: UtilisationRibTiersComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.utilisationRibTiers.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'utilisation-rib-tiers/:id',
        component: UtilisationRibTiersDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.utilisationRibTiers.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const utilisationRibTiersPopupRoute: Routes = [
    {
        path: 'utilisation-rib-tiers-new',
        component: UtilisationRibTiersPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.utilisationRibTiers.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'utilisation-rib-tiers/:id/edit',
        component: UtilisationRibTiersPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.utilisationRibTiers.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'utilisation-rib-tiers/:id/delete',
        component: UtilisationRibTiersDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.utilisationRibTiers.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

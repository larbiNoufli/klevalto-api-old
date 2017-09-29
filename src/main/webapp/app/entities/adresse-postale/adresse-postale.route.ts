import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { AdressePostaleComponent } from './adresse-postale.component';
import { AdressePostaleDetailComponent } from './adresse-postale-detail.component';
import { AdressePostalePopupComponent } from './adresse-postale-dialog.component';
import { AdressePostaleDeletePopupComponent } from './adresse-postale-delete-dialog.component';

export const adressePostaleRoute: Routes = [
    {
        path: 'adresse-postale',
        component: AdressePostaleComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.adressePostale.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'adresse-postale/:id',
        component: AdressePostaleDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.adressePostale.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const adressePostalePopupRoute: Routes = [
    {
        path: 'adresse-postale-new',
        component: AdressePostalePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.adressePostale.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'adresse-postale/:id/edit',
        component: AdressePostalePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.adressePostale.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'adresse-postale/:id/delete',
        component: AdressePostaleDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.adressePostale.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { LocataireBienComponent } from './locataire-bien.component';
import { LocataireBienDetailComponent } from './locataire-bien-detail.component';
import { LocataireBienPopupComponent } from './locataire-bien-dialog.component';
import { LocataireBienDeletePopupComponent } from './locataire-bien-delete-dialog.component';

export const locataireBienRoute: Routes = [
    {
        path: 'locataire-bien',
        component: LocataireBienComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.locataireBien.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'locataire-bien/:id',
        component: LocataireBienDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.locataireBien.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const locataireBienPopupRoute: Routes = [
    {
        path: 'locataire-bien-new',
        component: LocataireBienPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.locataireBien.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'locataire-bien/:id/edit',
        component: LocataireBienPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.locataireBien.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'locataire-bien/:id/delete',
        component: LocataireBienDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.locataireBien.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

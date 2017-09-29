import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { BienComponent } from './bien.component';
import { BienDetailComponent } from './bien-detail.component';
import { BienPopupComponent } from './bien-dialog.component';
import { BienDeletePopupComponent } from './bien-delete-dialog.component';

export const bienRoute: Routes = [
    {
        path: 'bien',
        component: BienComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.bien.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'bien/:id',
        component: BienDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.bien.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const bienPopupRoute: Routes = [
    {
        path: 'bien-new',
        component: BienPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.bien.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'bien/:id/edit',
        component: BienPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.bien.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'bien/:id/delete',
        component: BienDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.bien.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

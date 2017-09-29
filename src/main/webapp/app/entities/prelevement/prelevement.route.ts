import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { PrelevementComponent } from './prelevement.component';
import { PrelevementDetailComponent } from './prelevement-detail.component';
import { PrelevementPopupComponent } from './prelevement-dialog.component';
import { PrelevementDeletePopupComponent } from './prelevement-delete-dialog.component';

export const prelevementRoute: Routes = [
    {
        path: 'prelevement',
        component: PrelevementComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.prelevement.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'prelevement/:id',
        component: PrelevementDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.prelevement.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const prelevementPopupRoute: Routes = [
    {
        path: 'prelevement-new',
        component: PrelevementPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.prelevement.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'prelevement/:id/edit',
        component: PrelevementPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.prelevement.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'prelevement/:id/delete',
        component: PrelevementDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.prelevement.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

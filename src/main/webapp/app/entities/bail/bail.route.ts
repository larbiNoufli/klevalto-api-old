import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { BailComponent } from './bail.component';
import { BailDetailComponent } from './bail-detail.component';
import { BailPopupComponent } from './bail-dialog.component';
import { BailDeletePopupComponent } from './bail-delete-dialog.component';

export const bailRoute: Routes = [
    {
        path: 'bail',
        component: BailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.bail.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'bail/:id',
        component: BailDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.bail.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const bailPopupRoute: Routes = [
    {
        path: 'bail-new',
        component: BailPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.bail.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'bail/:id/edit',
        component: BailPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.bail.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'bail/:id/delete',
        component: BailDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.bail.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

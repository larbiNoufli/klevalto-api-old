import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { TypeBienComponent } from './type-bien.component';
import { TypeBienDetailComponent } from './type-bien-detail.component';
import { TypeBienPopupComponent } from './type-bien-dialog.component';
import { TypeBienDeletePopupComponent } from './type-bien-delete-dialog.component';

export const typeBienRoute: Routes = [
    {
        path: 'type-bien',
        component: TypeBienComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.typeBien.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'type-bien/:id',
        component: TypeBienDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.typeBien.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const typeBienPopupRoute: Routes = [
    {
        path: 'type-bien-new',
        component: TypeBienPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.typeBien.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'type-bien/:id/edit',
        component: TypeBienPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.typeBien.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'type-bien/:id/delete',
        component: TypeBienDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.typeBien.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

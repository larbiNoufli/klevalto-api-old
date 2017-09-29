import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { AssocBailBienComponent } from './assoc-bail-bien.component';
import { AssocBailBienDetailComponent } from './assoc-bail-bien-detail.component';
import { AssocBailBienPopupComponent } from './assoc-bail-bien-dialog.component';
import { AssocBailBienDeletePopupComponent } from './assoc-bail-bien-delete-dialog.component';

export const assocBailBienRoute: Routes = [
    {
        path: 'assoc-bail-bien',
        component: AssocBailBienComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.assocBailBien.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'assoc-bail-bien/:id',
        component: AssocBailBienDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.assocBailBien.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const assocBailBienPopupRoute: Routes = [
    {
        path: 'assoc-bail-bien-new',
        component: AssocBailBienPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.assocBailBien.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'assoc-bail-bien/:id/edit',
        component: AssocBailBienPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.assocBailBien.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'assoc-bail-bien/:id/delete',
        component: AssocBailBienDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.assocBailBien.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

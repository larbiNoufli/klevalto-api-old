import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ProfilComponent } from './profil.component';
import { ProfilDetailComponent } from './profil-detail.component';
import { ProfilPopupComponent } from './profil-dialog.component';
import { ProfilDeletePopupComponent } from './profil-delete-dialog.component';

export const profilRoute: Routes = [
    {
        path: 'profil',
        component: ProfilComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.profil.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'profil/:id',
        component: ProfilDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.profil.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const profilPopupRoute: Routes = [
    {
        path: 'profil-new',
        component: ProfilPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.profil.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'profil/:id/edit',
        component: ProfilPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.profil.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'profil/:id/delete',
        component: ProfilDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'sergicApp.profil.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

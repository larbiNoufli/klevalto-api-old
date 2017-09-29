import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SergicSharedModule } from '../../shared';
import {
    RoleService,
    RolePopupService,
    RoleComponent,
    RoleDetailComponent,
    RoleDialogComponent,
    RolePopupComponent,
    RoleDeletePopupComponent,
    RoleDeleteDialogComponent,
    roleRoute,
    rolePopupRoute,
} from './';

const ENTITY_STATES = [
    ...roleRoute,
    ...rolePopupRoute,
];

@NgModule({
    imports: [
        SergicSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        RoleComponent,
        RoleDetailComponent,
        RoleDialogComponent,
        RoleDeleteDialogComponent,
        RolePopupComponent,
        RoleDeletePopupComponent,
    ],
    entryComponents: [
        RoleComponent,
        RoleDialogComponent,
        RolePopupComponent,
        RoleDeleteDialogComponent,
        RoleDeletePopupComponent,
    ],
    providers: [
        RoleService,
        RolePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SergicRoleModule {}

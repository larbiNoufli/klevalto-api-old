import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SergicSharedModule } from '../../shared';
import {
    PoleService,
    PolePopupService,
    PoleComponent,
    PoleDetailComponent,
    PoleDialogComponent,
    PolePopupComponent,
    PoleDeletePopupComponent,
    PoleDeleteDialogComponent,
    poleRoute,
    polePopupRoute,
} from './';

const ENTITY_STATES = [
    ...poleRoute,
    ...polePopupRoute,
];

@NgModule({
    imports: [
        SergicSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        PoleComponent,
        PoleDetailComponent,
        PoleDialogComponent,
        PoleDeleteDialogComponent,
        PolePopupComponent,
        PoleDeletePopupComponent,
    ],
    entryComponents: [
        PoleComponent,
        PoleDialogComponent,
        PolePopupComponent,
        PoleDeleteDialogComponent,
        PoleDeletePopupComponent,
    ],
    providers: [
        PoleService,
        PolePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SergicPoleModule {}

import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SergicSharedModule } from '../../shared';
import {
    PrelevementService,
    PrelevementPopupService,
    PrelevementComponent,
    PrelevementDetailComponent,
    PrelevementDialogComponent,
    PrelevementPopupComponent,
    PrelevementDeletePopupComponent,
    PrelevementDeleteDialogComponent,
    prelevementRoute,
    prelevementPopupRoute,
} from './';

const ENTITY_STATES = [
    ...prelevementRoute,
    ...prelevementPopupRoute,
];

@NgModule({
    imports: [
        SergicSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        PrelevementComponent,
        PrelevementDetailComponent,
        PrelevementDialogComponent,
        PrelevementDeleteDialogComponent,
        PrelevementPopupComponent,
        PrelevementDeletePopupComponent,
    ],
    entryComponents: [
        PrelevementComponent,
        PrelevementDialogComponent,
        PrelevementPopupComponent,
        PrelevementDeleteDialogComponent,
        PrelevementDeletePopupComponent,
    ],
    providers: [
        PrelevementService,
        PrelevementPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SergicPrelevementModule {}

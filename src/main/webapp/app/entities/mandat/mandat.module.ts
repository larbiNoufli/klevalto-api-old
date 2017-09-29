import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SergicSharedModule } from '../../shared';
import {
    MandatService,
    MandatPopupService,
    MandatComponent,
    MandatDetailComponent,
    MandatDialogComponent,
    MandatPopupComponent,
    MandatDeletePopupComponent,
    MandatDeleteDialogComponent,
    mandatRoute,
    mandatPopupRoute,
} from './';

const ENTITY_STATES = [
    ...mandatRoute,
    ...mandatPopupRoute,
];

@NgModule({
    imports: [
        SergicSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        MandatComponent,
        MandatDetailComponent,
        MandatDialogComponent,
        MandatDeleteDialogComponent,
        MandatPopupComponent,
        MandatDeletePopupComponent,
    ],
    entryComponents: [
        MandatComponent,
        MandatDialogComponent,
        MandatPopupComponent,
        MandatDeleteDialogComponent,
        MandatDeletePopupComponent,
    ],
    providers: [
        MandatService,
        MandatPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SergicMandatModule {}

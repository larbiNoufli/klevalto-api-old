import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SergicSharedModule } from '../../shared';
import {
    BienService,
    BienPopupService,
    BienComponent,
    BienDetailComponent,
    BienDialogComponent,
    BienPopupComponent,
    BienDeletePopupComponent,
    BienDeleteDialogComponent,
    bienRoute,
    bienPopupRoute,
} from './';

const ENTITY_STATES = [
    ...bienRoute,
    ...bienPopupRoute,
];

@NgModule({
    imports: [
        SergicSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        BienComponent,
        BienDetailComponent,
        BienDialogComponent,
        BienDeleteDialogComponent,
        BienPopupComponent,
        BienDeletePopupComponent,
    ],
    entryComponents: [
        BienComponent,
        BienDialogComponent,
        BienPopupComponent,
        BienDeleteDialogComponent,
        BienDeletePopupComponent,
    ],
    providers: [
        BienService,
        BienPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SergicBienModule {}

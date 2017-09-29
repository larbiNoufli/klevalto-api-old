import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SergicSharedModule } from '../../shared';
import {
    AssocMandatBienService,
    AssocMandatBienPopupService,
    AssocMandatBienComponent,
    AssocMandatBienDetailComponent,
    AssocMandatBienDialogComponent,
    AssocMandatBienPopupComponent,
    AssocMandatBienDeletePopupComponent,
    AssocMandatBienDeleteDialogComponent,
    assocMandatBienRoute,
    assocMandatBienPopupRoute,
} from './';

const ENTITY_STATES = [
    ...assocMandatBienRoute,
    ...assocMandatBienPopupRoute,
];

@NgModule({
    imports: [
        SergicSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AssocMandatBienComponent,
        AssocMandatBienDetailComponent,
        AssocMandatBienDialogComponent,
        AssocMandatBienDeleteDialogComponent,
        AssocMandatBienPopupComponent,
        AssocMandatBienDeletePopupComponent,
    ],
    entryComponents: [
        AssocMandatBienComponent,
        AssocMandatBienDialogComponent,
        AssocMandatBienPopupComponent,
        AssocMandatBienDeleteDialogComponent,
        AssocMandatBienDeletePopupComponent,
    ],
    providers: [
        AssocMandatBienService,
        AssocMandatBienPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SergicAssocMandatBienModule {}

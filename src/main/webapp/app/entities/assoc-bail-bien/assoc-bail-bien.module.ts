import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SergicSharedModule } from '../../shared';
import {
    AssocBailBienService,
    AssocBailBienPopupService,
    AssocBailBienComponent,
    AssocBailBienDetailComponent,
    AssocBailBienDialogComponent,
    AssocBailBienPopupComponent,
    AssocBailBienDeletePopupComponent,
    AssocBailBienDeleteDialogComponent,
    assocBailBienRoute,
    assocBailBienPopupRoute,
} from './';

const ENTITY_STATES = [
    ...assocBailBienRoute,
    ...assocBailBienPopupRoute,
];

@NgModule({
    imports: [
        SergicSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AssocBailBienComponent,
        AssocBailBienDetailComponent,
        AssocBailBienDialogComponent,
        AssocBailBienDeleteDialogComponent,
        AssocBailBienPopupComponent,
        AssocBailBienDeletePopupComponent,
    ],
    entryComponents: [
        AssocBailBienComponent,
        AssocBailBienDialogComponent,
        AssocBailBienPopupComponent,
        AssocBailBienDeleteDialogComponent,
        AssocBailBienDeletePopupComponent,
    ],
    providers: [
        AssocBailBienService,
        AssocBailBienPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SergicAssocBailBienModule {}

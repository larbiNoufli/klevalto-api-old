import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SergicSharedModule } from '../../shared';
import {
    LocataireBienService,
    LocataireBienPopupService,
    LocataireBienComponent,
    LocataireBienDetailComponent,
    LocataireBienDialogComponent,
    LocataireBienPopupComponent,
    LocataireBienDeletePopupComponent,
    LocataireBienDeleteDialogComponent,
    locataireBienRoute,
    locataireBienPopupRoute,
} from './';

const ENTITY_STATES = [
    ...locataireBienRoute,
    ...locataireBienPopupRoute,
];

@NgModule({
    imports: [
        SergicSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        LocataireBienComponent,
        LocataireBienDetailComponent,
        LocataireBienDialogComponent,
        LocataireBienDeleteDialogComponent,
        LocataireBienPopupComponent,
        LocataireBienDeletePopupComponent,
    ],
    entryComponents: [
        LocataireBienComponent,
        LocataireBienDialogComponent,
        LocataireBienPopupComponent,
        LocataireBienDeleteDialogComponent,
        LocataireBienDeletePopupComponent,
    ],
    providers: [
        LocataireBienService,
        LocataireBienPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SergicLocataireBienModule {}

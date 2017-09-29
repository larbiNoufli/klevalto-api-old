import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SergicSharedModule } from '../../shared';
import {
    AdressePostaleService,
    AdressePostalePopupService,
    AdressePostaleComponent,
    AdressePostaleDetailComponent,
    AdressePostaleDialogComponent,
    AdressePostalePopupComponent,
    AdressePostaleDeletePopupComponent,
    AdressePostaleDeleteDialogComponent,
    adressePostaleRoute,
    adressePostalePopupRoute,
} from './';

const ENTITY_STATES = [
    ...adressePostaleRoute,
    ...adressePostalePopupRoute,
];

@NgModule({
    imports: [
        SergicSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AdressePostaleComponent,
        AdressePostaleDetailComponent,
        AdressePostaleDialogComponent,
        AdressePostaleDeleteDialogComponent,
        AdressePostalePopupComponent,
        AdressePostaleDeletePopupComponent,
    ],
    entryComponents: [
        AdressePostaleComponent,
        AdressePostaleDialogComponent,
        AdressePostalePopupComponent,
        AdressePostaleDeleteDialogComponent,
        AdressePostaleDeletePopupComponent,
    ],
    providers: [
        AdressePostaleService,
        AdressePostalePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SergicAdressePostaleModule {}

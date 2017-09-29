import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SergicSharedModule } from '../../shared';
import {
    UtilisationRibTiersService,
    UtilisationRibTiersPopupService,
    UtilisationRibTiersComponent,
    UtilisationRibTiersDetailComponent,
    UtilisationRibTiersDialogComponent,
    UtilisationRibTiersPopupComponent,
    UtilisationRibTiersDeletePopupComponent,
    UtilisationRibTiersDeleteDialogComponent,
    utilisationRibTiersRoute,
    utilisationRibTiersPopupRoute,
} from './';

const ENTITY_STATES = [
    ...utilisationRibTiersRoute,
    ...utilisationRibTiersPopupRoute,
];

@NgModule({
    imports: [
        SergicSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        UtilisationRibTiersComponent,
        UtilisationRibTiersDetailComponent,
        UtilisationRibTiersDialogComponent,
        UtilisationRibTiersDeleteDialogComponent,
        UtilisationRibTiersPopupComponent,
        UtilisationRibTiersDeletePopupComponent,
    ],
    entryComponents: [
        UtilisationRibTiersComponent,
        UtilisationRibTiersDialogComponent,
        UtilisationRibTiersPopupComponent,
        UtilisationRibTiersDeleteDialogComponent,
        UtilisationRibTiersDeletePopupComponent,
    ],
    providers: [
        UtilisationRibTiersService,
        UtilisationRibTiersPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SergicUtilisationRibTiersModule {}

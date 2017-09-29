import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SergicSharedModule } from '../../shared';
import {
    TiersService,
    TiersPopupService,
    TiersComponent,
    TiersDetailComponent,
    TiersDialogComponent,
    TiersPopupComponent,
    TiersDeletePopupComponent,
    TiersDeleteDialogComponent,
    tiersRoute,
    tiersPopupRoute,
} from './';

const ENTITY_STATES = [
    ...tiersRoute,
    ...tiersPopupRoute,
];

@NgModule({
    imports: [
        SergicSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        TiersComponent,
        TiersDetailComponent,
        TiersDialogComponent,
        TiersDeleteDialogComponent,
        TiersPopupComponent,
        TiersDeletePopupComponent,
    ],
    entryComponents: [
        TiersComponent,
        TiersDialogComponent,
        TiersPopupComponent,
        TiersDeleteDialogComponent,
        TiersDeletePopupComponent,
    ],
    providers: [
        TiersService,
        TiersPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SergicTiersModule {}

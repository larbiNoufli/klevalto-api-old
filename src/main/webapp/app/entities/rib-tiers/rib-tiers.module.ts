import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SergicSharedModule } from '../../shared';
import {
    RibTiersService,
    RibTiersPopupService,
    RibTiersComponent,
    RibTiersDetailComponent,
    RibTiersDialogComponent,
    RibTiersPopupComponent,
    RibTiersDeletePopupComponent,
    RibTiersDeleteDialogComponent,
    ribTiersRoute,
    ribTiersPopupRoute,
} from './';

const ENTITY_STATES = [
    ...ribTiersRoute,
    ...ribTiersPopupRoute,
];

@NgModule({
    imports: [
        SergicSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        RibTiersComponent,
        RibTiersDetailComponent,
        RibTiersDialogComponent,
        RibTiersDeleteDialogComponent,
        RibTiersPopupComponent,
        RibTiersDeletePopupComponent,
    ],
    entryComponents: [
        RibTiersComponent,
        RibTiersDialogComponent,
        RibTiersPopupComponent,
        RibTiersDeleteDialogComponent,
        RibTiersDeletePopupComponent,
    ],
    providers: [
        RibTiersService,
        RibTiersPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SergicRibTiersModule {}

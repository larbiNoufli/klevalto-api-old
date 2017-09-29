import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SergicSharedModule } from '../../shared';
import {
    BailService,
    BailPopupService,
    BailComponent,
    BailDetailComponent,
    BailDialogComponent,
    BailPopupComponent,
    BailDeletePopupComponent,
    BailDeleteDialogComponent,
    bailRoute,
    bailPopupRoute,
} from './';

const ENTITY_STATES = [
    ...bailRoute,
    ...bailPopupRoute,
];

@NgModule({
    imports: [
        SergicSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        BailComponent,
        BailDetailComponent,
        BailDialogComponent,
        BailDeleteDialogComponent,
        BailPopupComponent,
        BailDeletePopupComponent,
    ],
    entryComponents: [
        BailComponent,
        BailDialogComponent,
        BailPopupComponent,
        BailDeleteDialogComponent,
        BailDeletePopupComponent,
    ],
    providers: [
        BailService,
        BailPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SergicBailModule {}

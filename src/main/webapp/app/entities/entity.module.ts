import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { SergicTiersModule } from './tiers/tiers.module';
import { SergicAdressePostaleModule } from './adresse-postale/adresse-postale.module';
import { SergicPoleModule } from './pole/pole.module';
import { SergicProfilModule } from './profil/profil.module';
import { SergicRoleModule } from './role/role.module';
import { SergicMandatModule } from './mandat/mandat.module';
import { SergicBailModule } from './bail/bail.module';
import { SergicBienModule } from './bien/bien.module';
import { SergicTypeBienModule } from './type-bien/type-bien.module';
import { SergicLocataireBienModule } from './locataire-bien/locataire-bien.module';
import { SergicAssocMandatBienModule } from './assoc-mandat-bien/assoc-mandat-bien.module';
import { SergicAssocBailBienModule } from './assoc-bail-bien/assoc-bail-bien.module';
import { SergicPrelevementModule } from './prelevement/prelevement.module';
import { SergicUtilisationRibTiersModule } from './utilisation-rib-tiers/utilisation-rib-tiers.module';
import { SergicRibTiersModule } from './rib-tiers/rib-tiers.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        SergicTiersModule,
        SergicAdressePostaleModule,
        SergicPoleModule,
        SergicProfilModule,
        SergicRoleModule,
        SergicMandatModule,
        SergicBailModule,
        SergicBienModule,
        SergicTypeBienModule,
        SergicLocataireBienModule,
        SergicAssocMandatBienModule,
        SergicAssocBailBienModule,
        SergicPrelevementModule,
        SergicUtilisationRibTiersModule,
        SergicRibTiersModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SergicEntityModule {}

import { BaseEntity } from './../../shared';

export class TypeBien implements BaseEntity {
    constructor(
        public id?: number,
        public codeTypeLot?: string,
        public libelle?: string,
        public libelleAnnonce?: string,
        public isHabitable?: boolean,
        public dureeVisiteLocation?: number,
        public dureeVisiteVente?: number,
        public ordreAffichage?: number,
        public isTypeLotPrincipal?: number,
        public isLotSurfaceVariable?: boolean,
        public habitationOuTertiaire?: string,
        public nombrePieces?: number,
        public libelleCourt?: string,
        public nbrChambreObligatoireAnnonce?: boolean,
        public isUsedInKlevalto?: boolean,
    ) {
        this.isHabitable = false;
        this.isLotSurfaceVariable = false;
        this.nbrChambreObligatoireAnnonce = false;
        this.isUsedInKlevalto = false;
    }
}

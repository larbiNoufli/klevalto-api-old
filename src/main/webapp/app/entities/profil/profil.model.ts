import { BaseEntity } from './../../shared';

export class Profil implements BaseEntity {
    constructor(
        public id?: number,
        public isActif?: boolean,
        public identifiantProspectLocataire?: string,
        public identifiantLocataire?: string,
        public identifiantBailleur?: string,
        public identifiantBailleurRecherche?: string,
        public identifiantPersonnelSergic?: string,
        public identifiantCoproprietaire?: string,
        public identifiantProprietaireLocationSaisonniere?: string,
        public identifiantIntervenant?: string,
        public profil?: BaseEntity,
        public role?: BaseEntity,
        public bien?: BaseEntity,
    ) {
        this.isActif = false;
    }
}

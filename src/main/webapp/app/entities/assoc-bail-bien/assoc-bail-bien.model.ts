import { BaseEntity } from './../../shared';

export class AssocBailBien implements BaseEntity {
    constructor(
        public id?: number,
        public isActif?: boolean,
        public datedebutjuridique?: any,
        public datefinjuridique?: any,
        public isValidated?: boolean,
        public validationDate?: any,
        public isRejected?: boolean,
        public refusalDate?: any,
        public valeurAchatDuBien?: number,
        public tiers?: BaseEntity,
        public bail?: BaseEntity,
        public bien?: BaseEntity,
    ) {
        this.isActif = false;
        this.isValidated = false;
        this.isRejected = false;
    }
}

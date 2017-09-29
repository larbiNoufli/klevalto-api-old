import { BaseEntity } from './../../shared';

export class Mandat implements BaseEntity {
    constructor(
        public id?: number,
        public isActif?: boolean,
        public datedebutjuridique?: boolean,
        public datefinjuridique?: any,
        public isValidated?: boolean,
        public validationDate?: any,
        public isRejected?: boolean,
        public refusalDate?: any,
        public valeurAchatDuBien?: number,
    ) {
        this.isActif = false;
        this.datedebutjuridique = false;
        this.isValidated = false;
        this.isRejected = false;
    }
}

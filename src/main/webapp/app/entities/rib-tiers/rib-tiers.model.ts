import { BaseEntity } from './../../shared';

export const enum Civilite {
    'M',
    'MME'
}

export class RibTiers implements BaseEntity {
    constructor(
        public id?: number,
        public domiciliation?: string,
        public codeBanque?: number,
        public codeGuichet?: number,
        public numCompte?: string,
        public cleRib?: string,
        public iban?: string,
        public bic?: string,
        public libelle?: string,
        public civilite?: Civilite,
        public titulaireDuCompte?: string,
        public numInterneMaya?: number,
    ) {
    }
}

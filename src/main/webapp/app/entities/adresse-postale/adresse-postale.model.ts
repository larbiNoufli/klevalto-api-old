import { BaseEntity } from './../../shared';

export class AdressePostale implements BaseEntity {
    constructor(
        public id?: number,
        public codePostal?: string,
        public nomVille?: string,
        public numeroVoie?: number,
        public complementAdresse?: string,
        public numINSEE?: string,
        public pays?: string,
    ) {
    }
}

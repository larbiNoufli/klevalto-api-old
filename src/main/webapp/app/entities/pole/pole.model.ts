import { BaseEntity } from './../../shared';

export class Pole implements BaseEntity {
    constructor(
        public id?: number,
        public codePole?: string,
        public libelle?: string,
        public adresseVolet1?: string,
        public adresseVolet2?: string,
        public adresseVolet3?: string,
        public adresseVolet4?: string,
        public adresseVolet5?: string,
        public adresseVolet6?: string,
        public adresseVolet7?: string,
        public codePostal?: string,
        public descriptif?: string,
        public ville?: string,
    ) {
    }
}

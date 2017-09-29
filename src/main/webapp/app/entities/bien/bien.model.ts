import { BaseEntity } from './../../shared';

export class Bien implements BaseEntity {
    constructor(
        public id?: number,
        public referenceBien?: string,
        public codeBien?: string,
        public dureeVisiteLocation?: number,
        public typeBien?: BaseEntity,
        public mandat?: BaseEntity,
        public adressePostale?: BaseEntity,
    ) {
    }
}

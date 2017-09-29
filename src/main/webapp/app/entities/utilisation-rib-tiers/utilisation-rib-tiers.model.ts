import { BaseEntity } from './../../shared';

export class UtilisationRibTiers implements BaseEntity {
    constructor(
        public id?: number,
        public identifiantMaya?: string,
        public jourPrelevement?: any,
        public numNationalEmetteur?: string,
        public numeroOrdre?: number,
        public prelevement?: BaseEntity,
        public ribTiers?: BaseEntity,
    ) {
    }
}

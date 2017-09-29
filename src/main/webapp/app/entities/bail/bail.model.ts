import { BaseEntity } from './../../shared';

export class Bail implements BaseEntity {
    constructor(
        public id?: number,
        public dateCreation?: any,
        public statutSignatureBailleur?: string,
        public dateDetransfertVersMaya?: any,
    ) {
    }
}

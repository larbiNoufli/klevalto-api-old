import { BaseEntity } from './../../shared';

export class Prelevement implements BaseEntity {
    constructor(
        public id?: number,
        public montantAPrelever?: number,
        public numeroOrdre?: number,
        public dateDuPrelevement?: any,
        public isRecurrent?: boolean,
        public dateDetransfertVersMaya?: any,
    ) {
        this.isRecurrent = false;
    }
}

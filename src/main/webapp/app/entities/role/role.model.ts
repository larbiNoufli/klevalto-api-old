import { BaseEntity } from './../../shared';

export class Role implements BaseEntity {
    constructor(
        public id?: number,
        public codeRole?: string,
        public nature?: string,
        public isMembreGestion?: string,
        public libelle?: string,
        public isMembreCS?: boolean,
    ) {
        this.isMembreCS = false;
    }
}

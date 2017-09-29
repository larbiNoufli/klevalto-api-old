import { BaseEntity } from './../../shared';

export const enum Civilite {
    'M',
    'MME'
}

export const enum RegimeMatrimonial {
    'PACSE',
    'SEPARE',
    'VEUF',
    'SANS_OBJET',
    'CELIBATAIRE',
    'DIVORCE',
    'CONCUBIN',
    'MARIE'
}

export class Tiers implements BaseEntity {
    constructor(
        public id?: number,
        public adresseMail?: string,
        public noMail?: boolean,
        public motDePasse?: string,
        public identifiantCompteTiers?: string,
        public civilite?: Civilite,
        public nom?: string,
        public prenom?: string,
        public telephoneMobile?: string,
        public telephoneFixe?: string,
        public isProspectLocataire?: boolean,
        public isLocataire?: boolean,
        public isBailleur?: boolean,
        public isBailleurRecherche?: boolean,
        public isPersonnelSergic?: boolean,
        public isAdministrateur?: boolean,
        public isAcquereur?: boolean,
        public isCoproprietaire?: boolean,
        public isProprietaire?: boolean,
        public isAutreActeur?: boolean,
        public isProprietaireLocationSaisonniere?: boolean,
        public isIntervenant?: boolean,
        public regimeMatrimonial?: RegimeMatrimonial,
        public employeur?: string,
        public profession?: string,
        public dateEmbauche?: any,
        public revenuMensuel?: number,
        public nombreMoisDeRevenu?: number,
        public dateDeNaissance?: any,
        public lieuDeNaissance?: string,
        public nationalite?: string,
        public isSeparated?: boolean,
        public dateDeMariage?: any,
        public lieuDeMariage?: string,
        public commentaires?: string,
        public smsAccepted?: boolean,
        public adressePostaleNPAI?: boolean,
        public adressePostale?: BaseEntity,
        public profil?: BaseEntity,
        public utilisationRibTiers?: BaseEntity,
    ) {
        this.noMail = false;
        this.isProspectLocataire = false;
        this.isLocataire = false;
        this.isBailleur = false;
        this.isBailleurRecherche = false;
        this.isPersonnelSergic = false;
        this.isAdministrateur = false;
        this.isAcquereur = false;
        this.isCoproprietaire = false;
        this.isProprietaire = false;
        this.isAutreActeur = false;
        this.isProprietaireLocationSaisonniere = false;
        this.isIntervenant = false;
        this.isSeparated = false;
        this.smsAccepted = false;
        this.adressePostaleNPAI = false;
    }
}

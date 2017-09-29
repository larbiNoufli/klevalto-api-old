package klevalto.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import klevalto.domain.enumeration.Civilite;

import klevalto.domain.enumeration.RegimeMatrimonial;

/**
 * A Tiers.
 */
@Entity
@Table(name = "tiers")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tiers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "adresse_mail")
    private String adresseMail;

    @Column(name = "no_mail")
    private Boolean noMail;

    @Column(name = "mot_de_passe")
    private String motDePasse;

    @Column(name = "identifiant_compte_tiers")
    private String identifiantCompteTiers;

    @Enumerated(EnumType.STRING)
    @Column(name = "civilite")
    private Civilite civilite;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "telephone_mobile")
    private String telephoneMobile;

    @Column(name = "telephone_fixe")
    private String telephoneFixe;

    @Column(name = "is_prospect_locataire")
    private Boolean isProspectLocataire;

    @Column(name = "is_locataire")
    private Boolean isLocataire;

    @Column(name = "is_bailleur")
    private Boolean isBailleur;

    @Column(name = "is_bailleur_recherche")
    private Boolean isBailleurRecherche;

    @Column(name = "is_personnel_sergic")
    private Boolean isPersonnelSergic;

    @Column(name = "is_administrateur")
    private Boolean isAdministrateur;

    @Column(name = "is_acquereur")
    private Boolean isAcquereur;

    @Column(name = "is_coproprietaire")
    private Boolean isCoproprietaire;

    @Column(name = "is_proprietaire")
    private Boolean isProprietaire;

    @Column(name = "is_autre_acteur")
    private Boolean isAutreActeur;

    @Column(name = "is_proprietaire_location_saisonniere")
    private Boolean isProprietaireLocationSaisonniere;

    @Column(name = "is_intervenant")
    private Boolean isIntervenant;

    @Enumerated(EnumType.STRING)
    @Column(name = "regime_matrimonial")
    private RegimeMatrimonial regimeMatrimonial;

    @Column(name = "employeur")
    private String employeur;

    @Column(name = "profession")
    private String profession;

    @Column(name = "date_embauche")
    private ZonedDateTime dateEmbauche;

    @Column(name = "revenu_mensuel")
    private Float revenuMensuel;

    @Column(name = "nombre_mois_de_revenu")
    private Integer nombreMoisDeRevenu;

    @Column(name = "date_de_naissance")
    private ZonedDateTime dateDeNaissance;

    @Column(name = "lieu_de_naissance")
    private String lieuDeNaissance;

    @Column(name = "nationalite")
    private String nationalite;

    @Column(name = "is_separated")
    private Boolean isSeparated;

    @Column(name = "date_de_mariage")
    private ZonedDateTime dateDeMariage;

    @Column(name = "lieu_de_mariage")
    private String lieuDeMariage;

    @Column(name = "commentaires")
    private String commentaires;

    @Column(name = "sms_accepted")
    private Boolean smsAccepted;

    @Column(name = "adresse_postale_npai")
    private Boolean adressePostaleNPAI;

    @ManyToOne
    private AdressePostale adressePostale;

    @ManyToOne
    private Profil profil;

    @ManyToOne
    private UtilisationRibTiers utilisationRibTiers;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public Tiers adresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
        return this;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public Boolean isNoMail() {
        return noMail;
    }

    public Tiers noMail(Boolean noMail) {
        this.noMail = noMail;
        return this;
    }

    public void setNoMail(Boolean noMail) {
        this.noMail = noMail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public Tiers motDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
        return this;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getIdentifiantCompteTiers() {
        return identifiantCompteTiers;
    }

    public Tiers identifiantCompteTiers(String identifiantCompteTiers) {
        this.identifiantCompteTiers = identifiantCompteTiers;
        return this;
    }

    public void setIdentifiantCompteTiers(String identifiantCompteTiers) {
        this.identifiantCompteTiers = identifiantCompteTiers;
    }

    public Civilite getCivilite() {
        return civilite;
    }

    public Tiers civilite(Civilite civilite) {
        this.civilite = civilite;
        return this;
    }

    public void setCivilite(Civilite civilite) {
        this.civilite = civilite;
    }

    public String getNom() {
        return nom;
    }

    public Tiers nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Tiers prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephoneMobile() {
        return telephoneMobile;
    }

    public Tiers telephoneMobile(String telephoneMobile) {
        this.telephoneMobile = telephoneMobile;
        return this;
    }

    public void setTelephoneMobile(String telephoneMobile) {
        this.telephoneMobile = telephoneMobile;
    }

    public String getTelephoneFixe() {
        return telephoneFixe;
    }

    public Tiers telephoneFixe(String telephoneFixe) {
        this.telephoneFixe = telephoneFixe;
        return this;
    }

    public void setTelephoneFixe(String telephoneFixe) {
        this.telephoneFixe = telephoneFixe;
    }

    public Boolean isIsProspectLocataire() {
        return isProspectLocataire;
    }

    public Tiers isProspectLocataire(Boolean isProspectLocataire) {
        this.isProspectLocataire = isProspectLocataire;
        return this;
    }

    public void setIsProspectLocataire(Boolean isProspectLocataire) {
        this.isProspectLocataire = isProspectLocataire;
    }

    public Boolean isIsLocataire() {
        return isLocataire;
    }

    public Tiers isLocataire(Boolean isLocataire) {
        this.isLocataire = isLocataire;
        return this;
    }

    public void setIsLocataire(Boolean isLocataire) {
        this.isLocataire = isLocataire;
    }

    public Boolean isIsBailleur() {
        return isBailleur;
    }

    public Tiers isBailleur(Boolean isBailleur) {
        this.isBailleur = isBailleur;
        return this;
    }

    public void setIsBailleur(Boolean isBailleur) {
        this.isBailleur = isBailleur;
    }

    public Boolean isIsBailleurRecherche() {
        return isBailleurRecherche;
    }

    public Tiers isBailleurRecherche(Boolean isBailleurRecherche) {
        this.isBailleurRecherche = isBailleurRecherche;
        return this;
    }

    public void setIsBailleurRecherche(Boolean isBailleurRecherche) {
        this.isBailleurRecherche = isBailleurRecherche;
    }

    public Boolean isIsPersonnelSergic() {
        return isPersonnelSergic;
    }

    public Tiers isPersonnelSergic(Boolean isPersonnelSergic) {
        this.isPersonnelSergic = isPersonnelSergic;
        return this;
    }

    public void setIsPersonnelSergic(Boolean isPersonnelSergic) {
        this.isPersonnelSergic = isPersonnelSergic;
    }

    public Boolean isIsAdministrateur() {
        return isAdministrateur;
    }

    public Tiers isAdministrateur(Boolean isAdministrateur) {
        this.isAdministrateur = isAdministrateur;
        return this;
    }

    public void setIsAdministrateur(Boolean isAdministrateur) {
        this.isAdministrateur = isAdministrateur;
    }

    public Boolean isIsAcquereur() {
        return isAcquereur;
    }

    public Tiers isAcquereur(Boolean isAcquereur) {
        this.isAcquereur = isAcquereur;
        return this;
    }

    public void setIsAcquereur(Boolean isAcquereur) {
        this.isAcquereur = isAcquereur;
    }

    public Boolean isIsCoproprietaire() {
        return isCoproprietaire;
    }

    public Tiers isCoproprietaire(Boolean isCoproprietaire) {
        this.isCoproprietaire = isCoproprietaire;
        return this;
    }

    public void setIsCoproprietaire(Boolean isCoproprietaire) {
        this.isCoproprietaire = isCoproprietaire;
    }

    public Boolean isIsProprietaire() {
        return isProprietaire;
    }

    public Tiers isProprietaire(Boolean isProprietaire) {
        this.isProprietaire = isProprietaire;
        return this;
    }

    public void setIsProprietaire(Boolean isProprietaire) {
        this.isProprietaire = isProprietaire;
    }

    public Boolean isIsAutreActeur() {
        return isAutreActeur;
    }

    public Tiers isAutreActeur(Boolean isAutreActeur) {
        this.isAutreActeur = isAutreActeur;
        return this;
    }

    public void setIsAutreActeur(Boolean isAutreActeur) {
        this.isAutreActeur = isAutreActeur;
    }

    public Boolean isIsProprietaireLocationSaisonniere() {
        return isProprietaireLocationSaisonniere;
    }

    public Tiers isProprietaireLocationSaisonniere(Boolean isProprietaireLocationSaisonniere) {
        this.isProprietaireLocationSaisonniere = isProprietaireLocationSaisonniere;
        return this;
    }

    public void setIsProprietaireLocationSaisonniere(Boolean isProprietaireLocationSaisonniere) {
        this.isProprietaireLocationSaisonniere = isProprietaireLocationSaisonniere;
    }

    public Boolean isIsIntervenant() {
        return isIntervenant;
    }

    public Tiers isIntervenant(Boolean isIntervenant) {
        this.isIntervenant = isIntervenant;
        return this;
    }

    public void setIsIntervenant(Boolean isIntervenant) {
        this.isIntervenant = isIntervenant;
    }

    public RegimeMatrimonial getRegimeMatrimonial() {
        return regimeMatrimonial;
    }

    public Tiers regimeMatrimonial(RegimeMatrimonial regimeMatrimonial) {
        this.regimeMatrimonial = regimeMatrimonial;
        return this;
    }

    public void setRegimeMatrimonial(RegimeMatrimonial regimeMatrimonial) {
        this.regimeMatrimonial = regimeMatrimonial;
    }

    public String getEmployeur() {
        return employeur;
    }

    public Tiers employeur(String employeur) {
        this.employeur = employeur;
        return this;
    }

    public void setEmployeur(String employeur) {
        this.employeur = employeur;
    }

    public String getProfession() {
        return profession;
    }

    public Tiers profession(String profession) {
        this.profession = profession;
        return this;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public ZonedDateTime getDateEmbauche() {
        return dateEmbauche;
    }

    public Tiers dateEmbauche(ZonedDateTime dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
        return this;
    }

    public void setDateEmbauche(ZonedDateTime dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public Float getRevenuMensuel() {
        return revenuMensuel;
    }

    public Tiers revenuMensuel(Float revenuMensuel) {
        this.revenuMensuel = revenuMensuel;
        return this;
    }

    public void setRevenuMensuel(Float revenuMensuel) {
        this.revenuMensuel = revenuMensuel;
    }

    public Integer getNombreMoisDeRevenu() {
        return nombreMoisDeRevenu;
    }

    public Tiers nombreMoisDeRevenu(Integer nombreMoisDeRevenu) {
        this.nombreMoisDeRevenu = nombreMoisDeRevenu;
        return this;
    }

    public void setNombreMoisDeRevenu(Integer nombreMoisDeRevenu) {
        this.nombreMoisDeRevenu = nombreMoisDeRevenu;
    }

    public ZonedDateTime getDateDeNaissance() {
        return dateDeNaissance;
    }

    public Tiers dateDeNaissance(ZonedDateTime dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
        return this;
    }

    public void setDateDeNaissance(ZonedDateTime dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getLieuDeNaissance() {
        return lieuDeNaissance;
    }

    public Tiers lieuDeNaissance(String lieuDeNaissance) {
        this.lieuDeNaissance = lieuDeNaissance;
        return this;
    }

    public void setLieuDeNaissance(String lieuDeNaissance) {
        this.lieuDeNaissance = lieuDeNaissance;
    }

    public String getNationalite() {
        return nationalite;
    }

    public Tiers nationalite(String nationalite) {
        this.nationalite = nationalite;
        return this;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public Boolean isIsSeparated() {
        return isSeparated;
    }

    public Tiers isSeparated(Boolean isSeparated) {
        this.isSeparated = isSeparated;
        return this;
    }

    public void setIsSeparated(Boolean isSeparated) {
        this.isSeparated = isSeparated;
    }

    public ZonedDateTime getDateDeMariage() {
        return dateDeMariage;
    }

    public Tiers dateDeMariage(ZonedDateTime dateDeMariage) {
        this.dateDeMariage = dateDeMariage;
        return this;
    }

    public void setDateDeMariage(ZonedDateTime dateDeMariage) {
        this.dateDeMariage = dateDeMariage;
    }

    public String getLieuDeMariage() {
        return lieuDeMariage;
    }

    public Tiers lieuDeMariage(String lieuDeMariage) {
        this.lieuDeMariage = lieuDeMariage;
        return this;
    }

    public void setLieuDeMariage(String lieuDeMariage) {
        this.lieuDeMariage = lieuDeMariage;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public Tiers commentaires(String commentaires) {
        this.commentaires = commentaires;
        return this;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public Boolean isSmsAccepted() {
        return smsAccepted;
    }

    public Tiers smsAccepted(Boolean smsAccepted) {
        this.smsAccepted = smsAccepted;
        return this;
    }

    public void setSmsAccepted(Boolean smsAccepted) {
        this.smsAccepted = smsAccepted;
    }

    public Boolean isAdressePostaleNPAI() {
        return adressePostaleNPAI;
    }

    public Tiers adressePostaleNPAI(Boolean adressePostaleNPAI) {
        this.adressePostaleNPAI = adressePostaleNPAI;
        return this;
    }

    public void setAdressePostaleNPAI(Boolean adressePostaleNPAI) {
        this.adressePostaleNPAI = adressePostaleNPAI;
    }

    public AdressePostale getAdressePostale() {
        return adressePostale;
    }

    public Tiers adressePostale(AdressePostale adressePostale) {
        this.adressePostale = adressePostale;
        return this;
    }

    public void setAdressePostale(AdressePostale adressePostale) {
        this.adressePostale = adressePostale;
    }

    public Profil getProfil() {
        return profil;
    }

    public Tiers profil(Profil profil) {
        this.profil = profil;
        return this;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public UtilisationRibTiers getUtilisationRibTiers() {
        return utilisationRibTiers;
    }

    public Tiers utilisationRibTiers(UtilisationRibTiers utilisationRibTiers) {
        this.utilisationRibTiers = utilisationRibTiers;
        return this;
    }

    public void setUtilisationRibTiers(UtilisationRibTiers utilisationRibTiers) {
        this.utilisationRibTiers = utilisationRibTiers;
    }
    // jhipster-needle-entity-add-getters-setters - Jhipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tiers tiers = (Tiers) o;
        if (tiers.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tiers.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Tiers{" +
            "id=" + getId() +
            ", adresseMail='" + getAdresseMail() + "'" +
            ", noMail='" + isNoMail() + "'" +
            ", motDePasse='" + getMotDePasse() + "'" +
            ", identifiantCompteTiers='" + getIdentifiantCompteTiers() + "'" +
            ", civilite='" + getCivilite() + "'" +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", telephoneMobile='" + getTelephoneMobile() + "'" +
            ", telephoneFixe='" + getTelephoneFixe() + "'" +
            ", isProspectLocataire='" + isIsProspectLocataire() + "'" +
            ", isLocataire='" + isIsLocataire() + "'" +
            ", isBailleur='" + isIsBailleur() + "'" +
            ", isBailleurRecherche='" + isIsBailleurRecherche() + "'" +
            ", isPersonnelSergic='" + isIsPersonnelSergic() + "'" +
            ", isAdministrateur='" + isIsAdministrateur() + "'" +
            ", isAcquereur='" + isIsAcquereur() + "'" +
            ", isCoproprietaire='" + isIsCoproprietaire() + "'" +
            ", isProprietaire='" + isIsProprietaire() + "'" +
            ", isAutreActeur='" + isIsAutreActeur() + "'" +
            ", isProprietaireLocationSaisonniere='" + isIsProprietaireLocationSaisonniere() + "'" +
            ", isIntervenant='" + isIsIntervenant() + "'" +
            ", regimeMatrimonial='" + getRegimeMatrimonial() + "'" +
            ", employeur='" + getEmployeur() + "'" +
            ", profession='" + getProfession() + "'" +
            ", dateEmbauche='" + getDateEmbauche() + "'" +
            ", revenuMensuel='" + getRevenuMensuel() + "'" +
            ", nombreMoisDeRevenu='" + getNombreMoisDeRevenu() + "'" +
            ", dateDeNaissance='" + getDateDeNaissance() + "'" +
            ", lieuDeNaissance='" + getLieuDeNaissance() + "'" +
            ", nationalite='" + getNationalite() + "'" +
            ", isSeparated='" + isIsSeparated() + "'" +
            ", dateDeMariage='" + getDateDeMariage() + "'" +
            ", lieuDeMariage='" + getLieuDeMariage() + "'" +
            ", commentaires='" + getCommentaires() + "'" +
            ", smsAccepted='" + isSmsAccepted() + "'" +
            ", adressePostaleNPAI='" + isAdressePostaleNPAI() + "'" +
            "}";
    }
}

package klevalto.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Profil.
 */
@Entity
@Table(name = "profil")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Profil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "is_actif")
    private Boolean isActif;

    @Column(name = "identifiant_prospect_locataire")
    private String identifiantProspectLocataire;

    @Column(name = "identifiant_locataire")
    private String identifiantLocataire;

    @Column(name = "identifiant_bailleur")
    private String identifiantBailleur;

    @Column(name = "identifiant_bailleur_recherche")
    private String identifiantBailleurRecherche;

    @Column(name = "identifiant_personnel_sergic")
    private String identifiantPersonnelSergic;

    @Column(name = "identifiant_coproprietaire")
    private String identifiantCoproprietaire;

    @Column(name = "identifiant_proprietaire_location_saisonniere")
    private String identifiantProprietaireLocationSaisonniere;

    @Column(name = "identifiant_intervenant")
    private String identifiantIntervenant;

    @OneToOne
    @JoinColumn(unique = true)
    private Pole profil;

    @ManyToOne
    private Role role;

    @ManyToOne
    private Bien bien;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isIsActif() {
        return isActif;
    }

    public Profil isActif(Boolean isActif) {
        this.isActif = isActif;
        return this;
    }

    public void setIsActif(Boolean isActif) {
        this.isActif = isActif;
    }

    public String getIdentifiantProspectLocataire() {
        return identifiantProspectLocataire;
    }

    public Profil identifiantProspectLocataire(String identifiantProspectLocataire) {
        this.identifiantProspectLocataire = identifiantProspectLocataire;
        return this;
    }

    public void setIdentifiantProspectLocataire(String identifiantProspectLocataire) {
        this.identifiantProspectLocataire = identifiantProspectLocataire;
    }

    public String getIdentifiantLocataire() {
        return identifiantLocataire;
    }

    public Profil identifiantLocataire(String identifiantLocataire) {
        this.identifiantLocataire = identifiantLocataire;
        return this;
    }

    public void setIdentifiantLocataire(String identifiantLocataire) {
        this.identifiantLocataire = identifiantLocataire;
    }

    public String getIdentifiantBailleur() {
        return identifiantBailleur;
    }

    public Profil identifiantBailleur(String identifiantBailleur) {
        this.identifiantBailleur = identifiantBailleur;
        return this;
    }

    public void setIdentifiantBailleur(String identifiantBailleur) {
        this.identifiantBailleur = identifiantBailleur;
    }

    public String getIdentifiantBailleurRecherche() {
        return identifiantBailleurRecherche;
    }

    public Profil identifiantBailleurRecherche(String identifiantBailleurRecherche) {
        this.identifiantBailleurRecherche = identifiantBailleurRecherche;
        return this;
    }

    public void setIdentifiantBailleurRecherche(String identifiantBailleurRecherche) {
        this.identifiantBailleurRecherche = identifiantBailleurRecherche;
    }

    public String getIdentifiantPersonnelSergic() {
        return identifiantPersonnelSergic;
    }

    public Profil identifiantPersonnelSergic(String identifiantPersonnelSergic) {
        this.identifiantPersonnelSergic = identifiantPersonnelSergic;
        return this;
    }

    public void setIdentifiantPersonnelSergic(String identifiantPersonnelSergic) {
        this.identifiantPersonnelSergic = identifiantPersonnelSergic;
    }

    public String getIdentifiantCoproprietaire() {
        return identifiantCoproprietaire;
    }

    public Profil identifiantCoproprietaire(String identifiantCoproprietaire) {
        this.identifiantCoproprietaire = identifiantCoproprietaire;
        return this;
    }

    public void setIdentifiantCoproprietaire(String identifiantCoproprietaire) {
        this.identifiantCoproprietaire = identifiantCoproprietaire;
    }

    public String getIdentifiantProprietaireLocationSaisonniere() {
        return identifiantProprietaireLocationSaisonniere;
    }

    public Profil identifiantProprietaireLocationSaisonniere(String identifiantProprietaireLocationSaisonniere) {
        this.identifiantProprietaireLocationSaisonniere = identifiantProprietaireLocationSaisonniere;
        return this;
    }

    public void setIdentifiantProprietaireLocationSaisonniere(String identifiantProprietaireLocationSaisonniere) {
        this.identifiantProprietaireLocationSaisonniere = identifiantProprietaireLocationSaisonniere;
    }

    public String getIdentifiantIntervenant() {
        return identifiantIntervenant;
    }

    public Profil identifiantIntervenant(String identifiantIntervenant) {
        this.identifiantIntervenant = identifiantIntervenant;
        return this;
    }

    public void setIdentifiantIntervenant(String identifiantIntervenant) {
        this.identifiantIntervenant = identifiantIntervenant;
    }

    public Pole getProfil() {
        return profil;
    }

    public Profil profil(Pole pole) {
        this.profil = pole;
        return this;
    }

    public void setProfil(Pole pole) {
        this.profil = pole;
    }

    public Role getRole() {
        return role;
    }

    public Profil role(Role role) {
        this.role = role;
        return this;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Bien getBien() {
        return bien;
    }

    public Profil bien(Bien bien) {
        this.bien = bien;
        return this;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
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
        Profil profil = (Profil) o;
        if (profil.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), profil.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Profil{" +
            "id=" + getId() +
            ", isActif='" + isIsActif() + "'" +
            ", identifiantProspectLocataire='" + getIdentifiantProspectLocataire() + "'" +
            ", identifiantLocataire='" + getIdentifiantLocataire() + "'" +
            ", identifiantBailleur='" + getIdentifiantBailleur() + "'" +
            ", identifiantBailleurRecherche='" + getIdentifiantBailleurRecherche() + "'" +
            ", identifiantPersonnelSergic='" + getIdentifiantPersonnelSergic() + "'" +
            ", identifiantCoproprietaire='" + getIdentifiantCoproprietaire() + "'" +
            ", identifiantProprietaireLocationSaisonniere='" + getIdentifiantProprietaireLocationSaisonniere() + "'" +
            ", identifiantIntervenant='" + getIdentifiantIntervenant() + "'" +
            "}";
    }
}

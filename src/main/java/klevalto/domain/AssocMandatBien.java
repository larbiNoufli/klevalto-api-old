package klevalto.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AssocMandatBien.
 */
@Entity
@Table(name = "assoc_mandat_bien")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AssocMandatBien implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "is_actif")
    private Boolean isActif;

    @Column(name = "datedebutjuridique")
    private ZonedDateTime datedebutjuridique;

    @Column(name = "datefinjuridique")
    private ZonedDateTime datefinjuridique;

    @Column(name = "is_validated")
    private Boolean isValidated;

    @Column(name = "validation_date")
    private ZonedDateTime validationDate;

    @Column(name = "is_rejected")
    private Boolean isRejected;

    @Column(name = "refusal_date")
    private ZonedDateTime refusalDate;

    @Column(name = "valeur_achat_du_bien")
    private Double valeurAchatDuBien;

    @ManyToOne
    private Tiers tiers;

    @ManyToOne
    private Mandat mandat;

    @ManyToOne
    private Bien bien;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isIsActif() {
        return isActif;
    }

    public AssocMandatBien isActif(Boolean isActif) {
        this.isActif = isActif;
        return this;
    }

    public void setIsActif(Boolean isActif) {
        this.isActif = isActif;
    }

    public ZonedDateTime getDatedebutjuridique() {
        return datedebutjuridique;
    }

    public AssocMandatBien datedebutjuridique(ZonedDateTime datedebutjuridique) {
        this.datedebutjuridique = datedebutjuridique;
        return this;
    }

    public void setDatedebutjuridique(ZonedDateTime datedebutjuridique) {
        this.datedebutjuridique = datedebutjuridique;
    }

    public ZonedDateTime getDatefinjuridique() {
        return datefinjuridique;
    }

    public AssocMandatBien datefinjuridique(ZonedDateTime datefinjuridique) {
        this.datefinjuridique = datefinjuridique;
        return this;
    }

    public void setDatefinjuridique(ZonedDateTime datefinjuridique) {
        this.datefinjuridique = datefinjuridique;
    }

    public Boolean isIsValidated() {
        return isValidated;
    }

    public AssocMandatBien isValidated(Boolean isValidated) {
        this.isValidated = isValidated;
        return this;
    }

    public void setIsValidated(Boolean isValidated) {
        this.isValidated = isValidated;
    }

    public ZonedDateTime getValidationDate() {
        return validationDate;
    }

    public AssocMandatBien validationDate(ZonedDateTime validationDate) {
        this.validationDate = validationDate;
        return this;
    }

    public void setValidationDate(ZonedDateTime validationDate) {
        this.validationDate = validationDate;
    }

    public Boolean isIsRejected() {
        return isRejected;
    }

    public AssocMandatBien isRejected(Boolean isRejected) {
        this.isRejected = isRejected;
        return this;
    }

    public void setIsRejected(Boolean isRejected) {
        this.isRejected = isRejected;
    }

    public ZonedDateTime getRefusalDate() {
        return refusalDate;
    }

    public AssocMandatBien refusalDate(ZonedDateTime refusalDate) {
        this.refusalDate = refusalDate;
        return this;
    }

    public void setRefusalDate(ZonedDateTime refusalDate) {
        this.refusalDate = refusalDate;
    }

    public Double getValeurAchatDuBien() {
        return valeurAchatDuBien;
    }

    public AssocMandatBien valeurAchatDuBien(Double valeurAchatDuBien) {
        this.valeurAchatDuBien = valeurAchatDuBien;
        return this;
    }

    public void setValeurAchatDuBien(Double valeurAchatDuBien) {
        this.valeurAchatDuBien = valeurAchatDuBien;
    }

    public Tiers getTiers() {
        return tiers;
    }

    public AssocMandatBien tiers(Tiers tiers) {
        this.tiers = tiers;
        return this;
    }

    public void setTiers(Tiers tiers) {
        this.tiers = tiers;
    }

    public Mandat getMandat() {
        return mandat;
    }

    public AssocMandatBien mandat(Mandat mandat) {
        this.mandat = mandat;
        return this;
    }

    public void setMandat(Mandat mandat) {
        this.mandat = mandat;
    }

    public Bien getBien() {
        return bien;
    }

    public AssocMandatBien bien(Bien bien) {
        this.bien = bien;
        return this;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AssocMandatBien assocMandatBien = (AssocMandatBien) o;
        if (assocMandatBien.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), assocMandatBien.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AssocMandatBien{" +
            "id=" + getId() +
            ", isActif='" + isIsActif() + "'" +
            ", datedebutjuridique='" + getDatedebutjuridique() + "'" +
            ", datefinjuridique='" + getDatefinjuridique() + "'" +
            ", isValidated='" + isIsValidated() + "'" +
            ", validationDate='" + getValidationDate() + "'" +
            ", isRejected='" + isIsRejected() + "'" +
            ", refusalDate='" + getRefusalDate() + "'" +
            ", valeurAchatDuBien='" + getValeurAchatDuBien() + "'" +
            "}";
    }
}

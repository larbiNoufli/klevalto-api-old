package klevalto.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Mandat.
 */
@Entity
@Table(name = "mandat")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Mandat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "is_actif")
    private Boolean isActif;

    @Column(name = "datedebutjuridique")
    private Boolean datedebutjuridique;

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
    private Float valeurAchatDuBien;

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

    public Mandat isActif(Boolean isActif) {
        this.isActif = isActif;
        return this;
    }

    public void setIsActif(Boolean isActif) {
        this.isActif = isActif;
    }

    public Boolean isDatedebutjuridique() {
        return datedebutjuridique;
    }

    public Mandat datedebutjuridique(Boolean datedebutjuridique) {
        this.datedebutjuridique = datedebutjuridique;
        return this;
    }

    public void setDatedebutjuridique(Boolean datedebutjuridique) {
        this.datedebutjuridique = datedebutjuridique;
    }

    public ZonedDateTime getDatefinjuridique() {
        return datefinjuridique;
    }

    public Mandat datefinjuridique(ZonedDateTime datefinjuridique) {
        this.datefinjuridique = datefinjuridique;
        return this;
    }

    public void setDatefinjuridique(ZonedDateTime datefinjuridique) {
        this.datefinjuridique = datefinjuridique;
    }

    public Boolean isIsValidated() {
        return isValidated;
    }

    public Mandat isValidated(Boolean isValidated) {
        this.isValidated = isValidated;
        return this;
    }

    public void setIsValidated(Boolean isValidated) {
        this.isValidated = isValidated;
    }

    public ZonedDateTime getValidationDate() {
        return validationDate;
    }

    public Mandat validationDate(ZonedDateTime validationDate) {
        this.validationDate = validationDate;
        return this;
    }

    public void setValidationDate(ZonedDateTime validationDate) {
        this.validationDate = validationDate;
    }

    public Boolean isIsRejected() {
        return isRejected;
    }

    public Mandat isRejected(Boolean isRejected) {
        this.isRejected = isRejected;
        return this;
    }

    public void setIsRejected(Boolean isRejected) {
        this.isRejected = isRejected;
    }

    public ZonedDateTime getRefusalDate() {
        return refusalDate;
    }

    public Mandat refusalDate(ZonedDateTime refusalDate) {
        this.refusalDate = refusalDate;
        return this;
    }

    public void setRefusalDate(ZonedDateTime refusalDate) {
        this.refusalDate = refusalDate;
    }

    public Float getValeurAchatDuBien() {
        return valeurAchatDuBien;
    }

    public Mandat valeurAchatDuBien(Float valeurAchatDuBien) {
        this.valeurAchatDuBien = valeurAchatDuBien;
        return this;
    }

    public void setValeurAchatDuBien(Float valeurAchatDuBien) {
        this.valeurAchatDuBien = valeurAchatDuBien;
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
        Mandat mandat = (Mandat) o;
        if (mandat.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mandat.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Mandat{" +
            "id=" + getId() +
            ", isActif='" + isIsActif() + "'" +
            ", datedebutjuridique='" + isDatedebutjuridique() + "'" +
            ", datefinjuridique='" + getDatefinjuridique() + "'" +
            ", isValidated='" + isIsValidated() + "'" +
            ", validationDate='" + getValidationDate() + "'" +
            ", isRejected='" + isIsRejected() + "'" +
            ", refusalDate='" + getRefusalDate() + "'" +
            ", valeurAchatDuBien='" + getValeurAchatDuBien() + "'" +
            "}";
    }
}

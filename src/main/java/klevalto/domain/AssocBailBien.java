package klevalto.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A AssocBailBien.
 */
@Entity
@Table(name = "assoc_bail_bien")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AssocBailBien implements Serializable {

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
    private Bail bail;

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

    public AssocBailBien isActif(Boolean isActif) {
        this.isActif = isActif;
        return this;
    }

    public void setIsActif(Boolean isActif) {
        this.isActif = isActif;
    }

    public ZonedDateTime getDatedebutjuridique() {
        return datedebutjuridique;
    }

    public AssocBailBien datedebutjuridique(ZonedDateTime datedebutjuridique) {
        this.datedebutjuridique = datedebutjuridique;
        return this;
    }

    public void setDatedebutjuridique(ZonedDateTime datedebutjuridique) {
        this.datedebutjuridique = datedebutjuridique;
    }

    public ZonedDateTime getDatefinjuridique() {
        return datefinjuridique;
    }

    public AssocBailBien datefinjuridique(ZonedDateTime datefinjuridique) {
        this.datefinjuridique = datefinjuridique;
        return this;
    }

    public void setDatefinjuridique(ZonedDateTime datefinjuridique) {
        this.datefinjuridique = datefinjuridique;
    }

    public Boolean isIsValidated() {
        return isValidated;
    }

    public AssocBailBien isValidated(Boolean isValidated) {
        this.isValidated = isValidated;
        return this;
    }

    public void setIsValidated(Boolean isValidated) {
        this.isValidated = isValidated;
    }

    public ZonedDateTime getValidationDate() {
        return validationDate;
    }

    public AssocBailBien validationDate(ZonedDateTime validationDate) {
        this.validationDate = validationDate;
        return this;
    }

    public void setValidationDate(ZonedDateTime validationDate) {
        this.validationDate = validationDate;
    }

    public Boolean isIsRejected() {
        return isRejected;
    }

    public AssocBailBien isRejected(Boolean isRejected) {
        this.isRejected = isRejected;
        return this;
    }

    public void setIsRejected(Boolean isRejected) {
        this.isRejected = isRejected;
    }

    public ZonedDateTime getRefusalDate() {
        return refusalDate;
    }

    public AssocBailBien refusalDate(ZonedDateTime refusalDate) {
        this.refusalDate = refusalDate;
        return this;
    }

    public void setRefusalDate(ZonedDateTime refusalDate) {
        this.refusalDate = refusalDate;
    }

    public Double getValeurAchatDuBien() {
        return valeurAchatDuBien;
    }

    public AssocBailBien valeurAchatDuBien(Double valeurAchatDuBien) {
        this.valeurAchatDuBien = valeurAchatDuBien;
        return this;
    }

    public void setValeurAchatDuBien(Double valeurAchatDuBien) {
        this.valeurAchatDuBien = valeurAchatDuBien;
    }

    public Tiers getTiers() {
        return tiers;
    }

    public AssocBailBien tiers(Tiers tiers) {
        this.tiers = tiers;
        return this;
    }

    public void setTiers(Tiers tiers) {
        this.tiers = tiers;
    }

    public Bail getBail() {
        return bail;
    }

    public AssocBailBien bail(Bail bail) {
        this.bail = bail;
        return this;
    }

    public void setBail(Bail bail) {
        this.bail = bail;
    }

    public Bien getBien() {
        return bien;
    }

    public AssocBailBien bien(Bien bien) {
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
        AssocBailBien assocBailBien = (AssocBailBien) o;
        if (assocBailBien.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), assocBailBien.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AssocBailBien{" +
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

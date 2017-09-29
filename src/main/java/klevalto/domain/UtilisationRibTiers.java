package klevalto.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A UtilisationRibTiers.
 */
@Entity
@Table(name = "utilisation_rib_tiers")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UtilisationRibTiers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "identifiant_maya")
    private String identifiantMaya;

    @Column(name = "jour_prelevement")
    private ZonedDateTime jourPrelevement;

    @Column(name = "num_national_emetteur")
    private String numNationalEmetteur;

    @Column(name = "numero_ordre")
    private Integer numeroOrdre;

    @ManyToOne
    private Prelevement prelevement;

    @ManyToOne
    private RibTiers ribTiers;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifiantMaya() {
        return identifiantMaya;
    }

    public UtilisationRibTiers identifiantMaya(String identifiantMaya) {
        this.identifiantMaya = identifiantMaya;
        return this;
    }

    public void setIdentifiantMaya(String identifiantMaya) {
        this.identifiantMaya = identifiantMaya;
    }

    public ZonedDateTime getJourPrelevement() {
        return jourPrelevement;
    }

    public UtilisationRibTiers jourPrelevement(ZonedDateTime jourPrelevement) {
        this.jourPrelevement = jourPrelevement;
        return this;
    }

    public void setJourPrelevement(ZonedDateTime jourPrelevement) {
        this.jourPrelevement = jourPrelevement;
    }

    public String getNumNationalEmetteur() {
        return numNationalEmetteur;
    }

    public UtilisationRibTiers numNationalEmetteur(String numNationalEmetteur) {
        this.numNationalEmetteur = numNationalEmetteur;
        return this;
    }

    public void setNumNationalEmetteur(String numNationalEmetteur) {
        this.numNationalEmetteur = numNationalEmetteur;
    }

    public Integer getNumeroOrdre() {
        return numeroOrdre;
    }

    public UtilisationRibTiers numeroOrdre(Integer numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
        return this;
    }

    public void setNumeroOrdre(Integer numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }

    public Prelevement getPrelevement() {
        return prelevement;
    }

    public UtilisationRibTiers prelevement(Prelevement prelevement) {
        this.prelevement = prelevement;
        return this;
    }

    public void setPrelevement(Prelevement prelevement) {
        this.prelevement = prelevement;
    }

    public RibTiers getRibTiers() {
        return ribTiers;
    }

    public UtilisationRibTiers ribTiers(RibTiers ribTiers) {
        this.ribTiers = ribTiers;
        return this;
    }

    public void setRibTiers(RibTiers ribTiers) {
        this.ribTiers = ribTiers;
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
        UtilisationRibTiers utilisationRibTiers = (UtilisationRibTiers) o;
        if (utilisationRibTiers.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), utilisationRibTiers.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UtilisationRibTiers{" +
            "id=" + getId() +
            ", identifiantMaya='" + getIdentifiantMaya() + "'" +
            ", jourPrelevement='" + getJourPrelevement() + "'" +
            ", numNationalEmetteur='" + getNumNationalEmetteur() + "'" +
            ", numeroOrdre='" + getNumeroOrdre() + "'" +
            "}";
    }
}

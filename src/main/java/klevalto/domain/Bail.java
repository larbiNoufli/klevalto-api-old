package klevalto.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Bail.
 */
@Entity
@Table(name = "bail")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Bail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_creation")
    private ZonedDateTime dateCreation;

    @Column(name = "statut_signature_bailleur")
    private String statutSignatureBailleur;

    @Column(name = "date_detransfert_vers_maya")
    private ZonedDateTime dateDetransfertVersMaya;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateCreation() {
        return dateCreation;
    }

    public Bail dateCreation(ZonedDateTime dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    public void setDateCreation(ZonedDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getStatutSignatureBailleur() {
        return statutSignatureBailleur;
    }

    public Bail statutSignatureBailleur(String statutSignatureBailleur) {
        this.statutSignatureBailleur = statutSignatureBailleur;
        return this;
    }

    public void setStatutSignatureBailleur(String statutSignatureBailleur) {
        this.statutSignatureBailleur = statutSignatureBailleur;
    }

    public ZonedDateTime getDateDetransfertVersMaya() {
        return dateDetransfertVersMaya;
    }

    public Bail dateDetransfertVersMaya(ZonedDateTime dateDetransfertVersMaya) {
        this.dateDetransfertVersMaya = dateDetransfertVersMaya;
        return this;
    }

    public void setDateDetransfertVersMaya(ZonedDateTime dateDetransfertVersMaya) {
        this.dateDetransfertVersMaya = dateDetransfertVersMaya;
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
        Bail bail = (Bail) o;
        if (bail.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bail.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Bail{" +
            "id=" + getId() +
            ", dateCreation='" + getDateCreation() + "'" +
            ", statutSignatureBailleur='" + getStatutSignatureBailleur() + "'" +
            ", dateDetransfertVersMaya='" + getDateDetransfertVersMaya() + "'" +
            "}";
    }
}

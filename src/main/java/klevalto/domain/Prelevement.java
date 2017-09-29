package klevalto.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Prelevement.
 */
@Entity
@Table(name = "prelevement")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Prelevement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "montant_a_prelever")
    private Double montantAPrelever;

    @Column(name = "numero_ordre")
    private Integer numeroOrdre;

    @Column(name = "date_du_prelevement")
    private ZonedDateTime dateDuPrelevement;

    @Column(name = "is_recurrent")
    private Boolean isRecurrent;

    @Column(name = "date_detransfert_vers_maya")
    private ZonedDateTime dateDetransfertVersMaya;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMontantAPrelever() {
        return montantAPrelever;
    }

    public Prelevement montantAPrelever(Double montantAPrelever) {
        this.montantAPrelever = montantAPrelever;
        return this;
    }

    public void setMontantAPrelever(Double montantAPrelever) {
        this.montantAPrelever = montantAPrelever;
    }

    public Integer getNumeroOrdre() {
        return numeroOrdre;
    }

    public Prelevement numeroOrdre(Integer numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
        return this;
    }

    public void setNumeroOrdre(Integer numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }

    public ZonedDateTime getDateDuPrelevement() {
        return dateDuPrelevement;
    }

    public Prelevement dateDuPrelevement(ZonedDateTime dateDuPrelevement) {
        this.dateDuPrelevement = dateDuPrelevement;
        return this;
    }

    public void setDateDuPrelevement(ZonedDateTime dateDuPrelevement) {
        this.dateDuPrelevement = dateDuPrelevement;
    }

    public Boolean isIsRecurrent() {
        return isRecurrent;
    }

    public Prelevement isRecurrent(Boolean isRecurrent) {
        this.isRecurrent = isRecurrent;
        return this;
    }

    public void setIsRecurrent(Boolean isRecurrent) {
        this.isRecurrent = isRecurrent;
    }

    public ZonedDateTime getDateDetransfertVersMaya() {
        return dateDetransfertVersMaya;
    }

    public Prelevement dateDetransfertVersMaya(ZonedDateTime dateDetransfertVersMaya) {
        this.dateDetransfertVersMaya = dateDetransfertVersMaya;
        return this;
    }

    public void setDateDetransfertVersMaya(ZonedDateTime dateDetransfertVersMaya) {
        this.dateDetransfertVersMaya = dateDetransfertVersMaya;
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
        Prelevement prelevement = (Prelevement) o;
        if (prelevement.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prelevement.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Prelevement{" +
            "id=" + getId() +
            ", montantAPrelever='" + getMontantAPrelever() + "'" +
            ", numeroOrdre='" + getNumeroOrdre() + "'" +
            ", dateDuPrelevement='" + getDateDuPrelevement() + "'" +
            ", isRecurrent='" + isIsRecurrent() + "'" +
            ", dateDetransfertVersMaya='" + getDateDetransfertVersMaya() + "'" +
            "}";
    }
}

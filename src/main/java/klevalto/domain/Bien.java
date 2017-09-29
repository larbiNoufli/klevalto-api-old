package klevalto.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Bien.
 */
@Entity
@Table(name = "bien")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Bien implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "reference_bien")
    private String referenceBien;

    @Column(name = "code_bien")
    private String codeBien;

    @Column(name = "duree_visite_location")
    private Integer dureeVisiteLocation;

    @OneToOne
    @JoinColumn(unique = true)
    private TypeBien typeBien;

    @OneToOne
    @JoinColumn(unique = true)
    private Mandat mandat;

    @OneToOne
    @JoinColumn(unique = true)
    private AdressePostale adressePostale;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceBien() {
        return referenceBien;
    }

    public Bien referenceBien(String referenceBien) {
        this.referenceBien = referenceBien;
        return this;
    }

    public void setReferenceBien(String referenceBien) {
        this.referenceBien = referenceBien;
    }

    public String getCodeBien() {
        return codeBien;
    }

    public Bien codeBien(String codeBien) {
        this.codeBien = codeBien;
        return this;
    }

    public void setCodeBien(String codeBien) {
        this.codeBien = codeBien;
    }

    public Integer getDureeVisiteLocation() {
        return dureeVisiteLocation;
    }

    public Bien dureeVisiteLocation(Integer dureeVisiteLocation) {
        this.dureeVisiteLocation = dureeVisiteLocation;
        return this;
    }

    public void setDureeVisiteLocation(Integer dureeVisiteLocation) {
        this.dureeVisiteLocation = dureeVisiteLocation;
    }

    public TypeBien getTypeBien() {
        return typeBien;
    }

    public Bien typeBien(TypeBien typeBien) {
        this.typeBien = typeBien;
        return this;
    }

    public void setTypeBien(TypeBien typeBien) {
        this.typeBien = typeBien;
    }

    public Mandat getMandat() {
        return mandat;
    }

    public Bien mandat(Mandat mandat) {
        this.mandat = mandat;
        return this;
    }

    public void setMandat(Mandat mandat) {
        this.mandat = mandat;
    }

    public AdressePostale getAdressePostale() {
        return adressePostale;
    }

    public Bien adressePostale(AdressePostale adressePostale) {
        this.adressePostale = adressePostale;
        return this;
    }

    public void setAdressePostale(AdressePostale adressePostale) {
        this.adressePostale = adressePostale;
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
        Bien bien = (Bien) o;
        if (bien.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bien.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Bien{" +
            "id=" + getId() +
            ", referenceBien='" + getReferenceBien() + "'" +
            ", codeBien='" + getCodeBien() + "'" +
            ", dureeVisiteLocation='" + getDureeVisiteLocation() + "'" +
            "}";
    }
}

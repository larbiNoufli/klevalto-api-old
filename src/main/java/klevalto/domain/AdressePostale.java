package klevalto.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A AdressePostale.
 */
@Entity
@Table(name = "adresse_postale")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AdressePostale implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code_postal")
    private String codePostal;

    @Column(name = "nom_ville")
    private String nomVille;

    @Column(name = "numero_voie")
    private Long numeroVoie;

    @Column(name = "complement_adresse")
    private String complementAdresse;

    @Column(name = "num_insee")
    private String numINSEE;

    @Column(name = "pays")
    private String pays;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public AdressePostale codePostal(String codePostal) {
        this.codePostal = codePostal;
        return this;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getNomVille() {
        return nomVille;
    }

    public AdressePostale nomVille(String nomVille) {
        this.nomVille = nomVille;
        return this;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public Long getNumeroVoie() {
        return numeroVoie;
    }

    public AdressePostale numeroVoie(Long numeroVoie) {
        this.numeroVoie = numeroVoie;
        return this;
    }

    public void setNumeroVoie(Long numeroVoie) {
        this.numeroVoie = numeroVoie;
    }

    public String getComplementAdresse() {
        return complementAdresse;
    }

    public AdressePostale complementAdresse(String complementAdresse) {
        this.complementAdresse = complementAdresse;
        return this;
    }

    public void setComplementAdresse(String complementAdresse) {
        this.complementAdresse = complementAdresse;
    }

    public String getNumINSEE() {
        return numINSEE;
    }

    public AdressePostale numINSEE(String numINSEE) {
        this.numINSEE = numINSEE;
        return this;
    }

    public void setNumINSEE(String numINSEE) {
        this.numINSEE = numINSEE;
    }

    public String getPays() {
        return pays;
    }

    public AdressePostale pays(String pays) {
        this.pays = pays;
        return this;
    }

    public void setPays(String pays) {
        this.pays = pays;
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
        AdressePostale adressePostale = (AdressePostale) o;
        if (adressePostale.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adressePostale.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdressePostale{" +
            "id=" + getId() +
            ", codePostal='" + getCodePostal() + "'" +
            ", nomVille='" + getNomVille() + "'" +
            ", numeroVoie='" + getNumeroVoie() + "'" +
            ", complementAdresse='" + getComplementAdresse() + "'" +
            ", numINSEE='" + getNumINSEE() + "'" +
            ", pays='" + getPays() + "'" +
            "}";
    }
}

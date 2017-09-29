package klevalto.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Pole.
 */
@Entity
@Table(name = "pole")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code_pole")
    private String codePole;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "adresse_volet_1")
    private String adresseVolet1;

    @Column(name = "adresse_volet_2")
    private String adresseVolet2;

    @Column(name = "adresse_volet_3")
    private String adresseVolet3;

    @Column(name = "adresse_volet_4")
    private String adresseVolet4;

    @Column(name = "adresse_volet_5")
    private String adresseVolet5;

    @Column(name = "adresse_volet_6")
    private String adresseVolet6;

    @Column(name = "adresse_volet_7")
    private String adresseVolet7;

    @Column(name = "code_postal")
    private String codePostal;

    @Column(name = "descriptif")
    private String descriptif;

    @Column(name = "ville")
    private String ville;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodePole() {
        return codePole;
    }

    public Pole codePole(String codePole) {
        this.codePole = codePole;
        return this;
    }

    public void setCodePole(String codePole) {
        this.codePole = codePole;
    }

    public String getLibelle() {
        return libelle;
    }

    public Pole libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getAdresseVolet1() {
        return adresseVolet1;
    }

    public Pole adresseVolet1(String adresseVolet1) {
        this.adresseVolet1 = adresseVolet1;
        return this;
    }

    public void setAdresseVolet1(String adresseVolet1) {
        this.adresseVolet1 = adresseVolet1;
    }

    public String getAdresseVolet2() {
        return adresseVolet2;
    }

    public Pole adresseVolet2(String adresseVolet2) {
        this.adresseVolet2 = adresseVolet2;
        return this;
    }

    public void setAdresseVolet2(String adresseVolet2) {
        this.adresseVolet2 = adresseVolet2;
    }

    public String getAdresseVolet3() {
        return adresseVolet3;
    }

    public Pole adresseVolet3(String adresseVolet3) {
        this.adresseVolet3 = adresseVolet3;
        return this;
    }

    public void setAdresseVolet3(String adresseVolet3) {
        this.adresseVolet3 = adresseVolet3;
    }

    public String getAdresseVolet4() {
        return adresseVolet4;
    }

    public Pole adresseVolet4(String adresseVolet4) {
        this.adresseVolet4 = adresseVolet4;
        return this;
    }

    public void setAdresseVolet4(String adresseVolet4) {
        this.adresseVolet4 = adresseVolet4;
    }

    public String getAdresseVolet5() {
        return adresseVolet5;
    }

    public Pole adresseVolet5(String adresseVolet5) {
        this.adresseVolet5 = adresseVolet5;
        return this;
    }

    public void setAdresseVolet5(String adresseVolet5) {
        this.adresseVolet5 = adresseVolet5;
    }

    public String getAdresseVolet6() {
        return adresseVolet6;
    }

    public Pole adresseVolet6(String adresseVolet6) {
        this.adresseVolet6 = adresseVolet6;
        return this;
    }

    public void setAdresseVolet6(String adresseVolet6) {
        this.adresseVolet6 = adresseVolet6;
    }

    public String getAdresseVolet7() {
        return adresseVolet7;
    }

    public Pole adresseVolet7(String adresseVolet7) {
        this.adresseVolet7 = adresseVolet7;
        return this;
    }

    public void setAdresseVolet7(String adresseVolet7) {
        this.adresseVolet7 = adresseVolet7;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public Pole codePostal(String codePostal) {
        this.codePostal = codePostal;
        return this;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public Pole descriptif(String descriptif) {
        this.descriptif = descriptif;
        return this;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    public String getVille() {
        return ville;
    }

    public Pole ville(String ville) {
        this.ville = ville;
        return this;
    }

    public void setVille(String ville) {
        this.ville = ville;
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
        Pole pole = (Pole) o;
        if (pole.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pole.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Pole{" +
            "id=" + getId() +
            ", codePole='" + getCodePole() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", adresseVolet1='" + getAdresseVolet1() + "'" +
            ", adresseVolet2='" + getAdresseVolet2() + "'" +
            ", adresseVolet3='" + getAdresseVolet3() + "'" +
            ", adresseVolet4='" + getAdresseVolet4() + "'" +
            ", adresseVolet5='" + getAdresseVolet5() + "'" +
            ", adresseVolet6='" + getAdresseVolet6() + "'" +
            ", adresseVolet7='" + getAdresseVolet7() + "'" +
            ", codePostal='" + getCodePostal() + "'" +
            ", descriptif='" + getDescriptif() + "'" +
            ", ville='" + getVille() + "'" +
            "}";
    }
}

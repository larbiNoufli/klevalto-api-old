package klevalto.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import klevalto.domain.enumeration.Civilite;

/**
 * A RibTiers.
 */
@Entity
@Table(name = "rib_tiers")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RibTiers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "domiciliation")
    private String domiciliation;

    @Column(name = "code_banque")
    private Integer codeBanque;

    @Column(name = "code_guichet")
    private Integer codeGuichet;

    @Column(name = "num_compte")
    private String numCompte;

    @Column(name = "cle_rib")
    private String cleRib;

    @Column(name = "iban")
    private String iban;

    @Column(name = "bic")
    private String bic;

    @Column(name = "libelle")
    private String libelle;

    @Enumerated(EnumType.STRING)
    @Column(name = "civilite")
    private Civilite civilite;

    @Column(name = "titulaire_du_compte")
    private String titulaireDuCompte;

    @Column(name = "num_interne_maya")
    private Long numInterneMaya;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomiciliation() {
        return domiciliation;
    }

    public RibTiers domiciliation(String domiciliation) {
        this.domiciliation = domiciliation;
        return this;
    }

    public void setDomiciliation(String domiciliation) {
        this.domiciliation = domiciliation;
    }

    public Integer getCodeBanque() {
        return codeBanque;
    }

    public RibTiers codeBanque(Integer codeBanque) {
        this.codeBanque = codeBanque;
        return this;
    }

    public void setCodeBanque(Integer codeBanque) {
        this.codeBanque = codeBanque;
    }

    public Integer getCodeGuichet() {
        return codeGuichet;
    }

    public RibTiers codeGuichet(Integer codeGuichet) {
        this.codeGuichet = codeGuichet;
        return this;
    }

    public void setCodeGuichet(Integer codeGuichet) {
        this.codeGuichet = codeGuichet;
    }

    public String getNumCompte() {
        return numCompte;
    }

    public RibTiers numCompte(String numCompte) {
        this.numCompte = numCompte;
        return this;
    }

    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }

    public String getCleRib() {
        return cleRib;
    }

    public RibTiers cleRib(String cleRib) {
        this.cleRib = cleRib;
        return this;
    }

    public void setCleRib(String cleRib) {
        this.cleRib = cleRib;
    }

    public String getIban() {
        return iban;
    }

    public RibTiers iban(String iban) {
        this.iban = iban;
        return this;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public RibTiers bic(String bic) {
        this.bic = bic;
        return this;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getLibelle() {
        return libelle;
    }

    public RibTiers libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Civilite getCivilite() {
        return civilite;
    }

    public RibTiers civilite(Civilite civilite) {
        this.civilite = civilite;
        return this;
    }

    public void setCivilite(Civilite civilite) {
        this.civilite = civilite;
    }

    public String getTitulaireDuCompte() {
        return titulaireDuCompte;
    }

    public RibTiers titulaireDuCompte(String titulaireDuCompte) {
        this.titulaireDuCompte = titulaireDuCompte;
        return this;
    }

    public void setTitulaireDuCompte(String titulaireDuCompte) {
        this.titulaireDuCompte = titulaireDuCompte;
    }

    public Long getNumInterneMaya() {
        return numInterneMaya;
    }

    public RibTiers numInterneMaya(Long numInterneMaya) {
        this.numInterneMaya = numInterneMaya;
        return this;
    }

    public void setNumInterneMaya(Long numInterneMaya) {
        this.numInterneMaya = numInterneMaya;
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
        RibTiers ribTiers = (RibTiers) o;
        if (ribTiers.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ribTiers.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RibTiers{" +
            "id=" + getId() +
            ", domiciliation='" + getDomiciliation() + "'" +
            ", codeBanque='" + getCodeBanque() + "'" +
            ", codeGuichet='" + getCodeGuichet() + "'" +
            ", numCompte='" + getNumCompte() + "'" +
            ", cleRib='" + getCleRib() + "'" +
            ", iban='" + getIban() + "'" +
            ", bic='" + getBic() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", civilite='" + getCivilite() + "'" +
            ", titulaireDuCompte='" + getTitulaireDuCompte() + "'" +
            ", numInterneMaya='" + getNumInterneMaya() + "'" +
            "}";
    }
}

package klevalto.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Role.
 */
@Entity
@Table(name = "role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code_role")
    private String codeRole;

    @Column(name = "nature")
    private String nature;

    @Column(name = "is_membre_gestion")
    private String isMembreGestion;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "is_membre_cs")
    private Boolean isMembreCS;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeRole() {
        return codeRole;
    }

    public Role codeRole(String codeRole) {
        this.codeRole = codeRole;
        return this;
    }

    public void setCodeRole(String codeRole) {
        this.codeRole = codeRole;
    }

    public String getNature() {
        return nature;
    }

    public Role nature(String nature) {
        this.nature = nature;
        return this;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getIsMembreGestion() {
        return isMembreGestion;
    }

    public Role isMembreGestion(String isMembreGestion) {
        this.isMembreGestion = isMembreGestion;
        return this;
    }

    public void setIsMembreGestion(String isMembreGestion) {
        this.isMembreGestion = isMembreGestion;
    }

    public String getLibelle() {
        return libelle;
    }

    public Role libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Boolean isIsMembreCS() {
        return isMembreCS;
    }

    public Role isMembreCS(Boolean isMembreCS) {
        this.isMembreCS = isMembreCS;
        return this;
    }

    public void setIsMembreCS(Boolean isMembreCS) {
        this.isMembreCS = isMembreCS;
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
        Role role = (Role) o;
        if (role.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), role.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Role{" +
            "id=" + getId() +
            ", codeRole='" + getCodeRole() + "'" +
            ", nature='" + getNature() + "'" +
            ", isMembreGestion='" + getIsMembreGestion() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", isMembreCS='" + isIsMembreCS() + "'" +
            "}";
    }
}

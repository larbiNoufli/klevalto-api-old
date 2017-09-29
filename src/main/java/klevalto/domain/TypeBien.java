package klevalto.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A TypeBien.
 */
@Entity
@Table(name = "type_bien")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TypeBien implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code_type_lot")
    private String codeTypeLot;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "libelle_annonce")
    private String libelleAnnonce;

    @Column(name = "is_habitable")
    private Boolean isHabitable;

    @Column(name = "duree_visite_location")
    private Integer dureeVisiteLocation;

    @Column(name = "duree_visite_vente")
    private Integer dureeVisiteVente;

    @Column(name = "ordre_affichage")
    private Integer ordreAffichage;

    @Column(name = "is_type_lot_principal")
    private Integer isTypeLotPrincipal;

    @Column(name = "is_lot_surface_variable")
    private Boolean isLotSurfaceVariable;

    @Column(name = "habitation_ou_tertiaire")
    private String habitationOuTertiaire;

    @Column(name = "nombre_pieces")
    private Integer nombrePieces;

    @Column(name = "libelle_court")
    private String libelleCourt;

    @Column(name = "nbr_chambre_obligatoire_annonce")
    private Boolean nbrChambreObligatoireAnnonce;

    @Column(name = "is_used_in_klevalto")
    private Boolean isUsedInKlevalto;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeTypeLot() {
        return codeTypeLot;
    }

    public TypeBien codeTypeLot(String codeTypeLot) {
        this.codeTypeLot = codeTypeLot;
        return this;
    }

    public void setCodeTypeLot(String codeTypeLot) {
        this.codeTypeLot = codeTypeLot;
    }

    public String getLibelle() {
        return libelle;
    }

    public TypeBien libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelleAnnonce() {
        return libelleAnnonce;
    }

    public TypeBien libelleAnnonce(String libelleAnnonce) {
        this.libelleAnnonce = libelleAnnonce;
        return this;
    }

    public void setLibelleAnnonce(String libelleAnnonce) {
        this.libelleAnnonce = libelleAnnonce;
    }

    public Boolean isIsHabitable() {
        return isHabitable;
    }

    public TypeBien isHabitable(Boolean isHabitable) {
        this.isHabitable = isHabitable;
        return this;
    }

    public void setIsHabitable(Boolean isHabitable) {
        this.isHabitable = isHabitable;
    }

    public Integer getDureeVisiteLocation() {
        return dureeVisiteLocation;
    }

    public TypeBien dureeVisiteLocation(Integer dureeVisiteLocation) {
        this.dureeVisiteLocation = dureeVisiteLocation;
        return this;
    }

    public void setDureeVisiteLocation(Integer dureeVisiteLocation) {
        this.dureeVisiteLocation = dureeVisiteLocation;
    }

    public Integer getDureeVisiteVente() {
        return dureeVisiteVente;
    }

    public TypeBien dureeVisiteVente(Integer dureeVisiteVente) {
        this.dureeVisiteVente = dureeVisiteVente;
        return this;
    }

    public void setDureeVisiteVente(Integer dureeVisiteVente) {
        this.dureeVisiteVente = dureeVisiteVente;
    }

    public Integer getOrdreAffichage() {
        return ordreAffichage;
    }

    public TypeBien ordreAffichage(Integer ordreAffichage) {
        this.ordreAffichage = ordreAffichage;
        return this;
    }

    public void setOrdreAffichage(Integer ordreAffichage) {
        this.ordreAffichage = ordreAffichage;
    }

    public Integer getIsTypeLotPrincipal() {
        return isTypeLotPrincipal;
    }

    public TypeBien isTypeLotPrincipal(Integer isTypeLotPrincipal) {
        this.isTypeLotPrincipal = isTypeLotPrincipal;
        return this;
    }

    public void setIsTypeLotPrincipal(Integer isTypeLotPrincipal) {
        this.isTypeLotPrincipal = isTypeLotPrincipal;
    }

    public Boolean isIsLotSurfaceVariable() {
        return isLotSurfaceVariable;
    }

    public TypeBien isLotSurfaceVariable(Boolean isLotSurfaceVariable) {
        this.isLotSurfaceVariable = isLotSurfaceVariable;
        return this;
    }

    public void setIsLotSurfaceVariable(Boolean isLotSurfaceVariable) {
        this.isLotSurfaceVariable = isLotSurfaceVariable;
    }

    public String getHabitationOuTertiaire() {
        return habitationOuTertiaire;
    }

    public TypeBien habitationOuTertiaire(String habitationOuTertiaire) {
        this.habitationOuTertiaire = habitationOuTertiaire;
        return this;
    }

    public void setHabitationOuTertiaire(String habitationOuTertiaire) {
        this.habitationOuTertiaire = habitationOuTertiaire;
    }

    public Integer getNombrePieces() {
        return nombrePieces;
    }

    public TypeBien nombrePieces(Integer nombrePieces) {
        this.nombrePieces = nombrePieces;
        return this;
    }

    public void setNombrePieces(Integer nombrePieces) {
        this.nombrePieces = nombrePieces;
    }

    public String getLibelleCourt() {
        return libelleCourt;
    }

    public TypeBien libelleCourt(String libelleCourt) {
        this.libelleCourt = libelleCourt;
        return this;
    }

    public void setLibelleCourt(String libelleCourt) {
        this.libelleCourt = libelleCourt;
    }

    public Boolean isNbrChambreObligatoireAnnonce() {
        return nbrChambreObligatoireAnnonce;
    }

    public TypeBien nbrChambreObligatoireAnnonce(Boolean nbrChambreObligatoireAnnonce) {
        this.nbrChambreObligatoireAnnonce = nbrChambreObligatoireAnnonce;
        return this;
    }

    public void setNbrChambreObligatoireAnnonce(Boolean nbrChambreObligatoireAnnonce) {
        this.nbrChambreObligatoireAnnonce = nbrChambreObligatoireAnnonce;
    }

    public Boolean isIsUsedInKlevalto() {
        return isUsedInKlevalto;
    }

    public TypeBien isUsedInKlevalto(Boolean isUsedInKlevalto) {
        this.isUsedInKlevalto = isUsedInKlevalto;
        return this;
    }

    public void setIsUsedInKlevalto(Boolean isUsedInKlevalto) {
        this.isUsedInKlevalto = isUsedInKlevalto;
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
        TypeBien typeBien = (TypeBien) o;
        if (typeBien.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), typeBien.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TypeBien{" +
            "id=" + getId() +
            ", codeTypeLot='" + getCodeTypeLot() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", libelleAnnonce='" + getLibelleAnnonce() + "'" +
            ", isHabitable='" + isIsHabitable() + "'" +
            ", dureeVisiteLocation='" + getDureeVisiteLocation() + "'" +
            ", dureeVisiteVente='" + getDureeVisiteVente() + "'" +
            ", ordreAffichage='" + getOrdreAffichage() + "'" +
            ", isTypeLotPrincipal='" + getIsTypeLotPrincipal() + "'" +
            ", isLotSurfaceVariable='" + isIsLotSurfaceVariable() + "'" +
            ", habitationOuTertiaire='" + getHabitationOuTertiaire() + "'" +
            ", nombrePieces='" + getNombrePieces() + "'" +
            ", libelleCourt='" + getLibelleCourt() + "'" +
            ", nbrChambreObligatoireAnnonce='" + isNbrChambreObligatoireAnnonce() + "'" +
            ", isUsedInKlevalto='" + isIsUsedInKlevalto() + "'" +
            "}";
    }
}

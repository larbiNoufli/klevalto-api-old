package klevalto.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;


@ToString
@Data
public  class InfosBailPdfDTO extends InfosCommunesPdf {
	
	
	
	
	// Le Locataire
	
	/**
	 * Prenom du bailleur
	 */
	private String prenomLocataire;
	
	/**
	 * Nom du locataire
	 */
	private String nomLocataire;
	
	/**
	 * Adresse du locataire
	 */
	private String adresseLocataire;
	
	
	// Le bien 
	/**
	 * Type du bien 
	 */
	private String typeBien1;
	
	/**
	 * Type du bien
	 */
	private String typeBien2;
	
	/**
	 * surface en m² du bien
	 */
	private String surfaceBien;
	/**
	 * adresse postale du bien
	 */
	private String adresseBien;
	
	/**
	 * equipements du bien
	 */
	private String equipementsBien;
	
	
	/**
	 * Date de début du contrat
	 */
	private String dateEffetDuContrat;
	
	/**
	 * montant du depot de garantie
	 */
	private String depotGarantie;
	
	
	
	// Le loyer et charges
	
	/**
	 * montant en euros du loyer
	 */
	private String	montantLoyer;
	
	/**
	 * montant des charges en euros
	 */
	private String montantCharges;
	
	/**
	 * montant du loyer en lettre
	 */
	private String montantLoyerEnLettres;
	
	/**
	 * montant des charges en lettre
	 */
	private String montantChargesEnLettres;
	
	// infos du Bien 
	
	/**
	 * Regime juridique de l immeuble loi duflot etc
	 */
	private String regimeJuridiqueImmeuble;
	
	/**
	 * periode de construction avant 1930, aprés 1930 etc 
	 */
	private String periodeConstruction;
	
	/*
	 * nombre de piéce du bien
	 */
	private String nbPiecesHabitables;
	
	
	/*
	 * 
	 */
	private String autresPartiesLogementElementsEq;
	
	/**
	 * Mode de production de chauffage
	 */
	private String modeProdChauffage;
	
	/**
	 * Mode production eau chaude 
	 */
	private String modeProdEauChaudeSanitaire;
	
	/**
	 * 
	 */
	private String accessoiresUsagePrivatifLocataire;
	
	/**
	 * 
	 */
	private String accessoiresUsageCommun;
	
	/**
	 * 
	 */
	private String accesoiresInfoCommun;
	
	/**
	 * 
	 */
	private String montantLoyerRef;
	
	/**
	 * Montant du loyer majoré
	 */
	private String montantLoyerMajore;
	
	/**
	 * 
	 */
	private String complementLoyer;



	@Builder
	public InfosBailPdfDTO(String civiliteBailleur, String prenomBailleur, String nomBailleur, String adresseBailleur,
			String telephoneBailleur, String telephoneMobileBailleur, String mailBailleur, String nationaliteBailleur,
			String situationFamilialeBailleur, String prenomLocataire, String nomLocataire, String adresseLocataire,
			String typeBien1, String typeBien2, String surfaceBien, String adresseBien, String equipementsBien,
			String dateEffetDuContrat, String depotGarantie, String montantLoyer, String montantCharges,
			String montantLoyerEnLettres, String montantChargesEnLettres, String regimeJuridiqueImmeuble,
			String periodeConstruction, String nbPiecesHabitables, String autresPartiesLogementElementsEq,
			String modeProdChauffage, String modeProdEauChaudeSanitaire, String accessoiresUsagePrivatifLocataire,
			String accessoiresUsageCommun, String accesoiresInfoCommun, String montantLoyerRef,
			String montantLoyerMajore, String complementLoyer) {
		super(civiliteBailleur, prenomBailleur, nomBailleur, adresseBailleur, telephoneBailleur,
				telephoneMobileBailleur, mailBailleur, nationaliteBailleur, situationFamilialeBailleur);
		this.prenomLocataire = prenomLocataire;
		this.nomLocataire = nomLocataire;
		this.adresseLocataire = adresseLocataire;
		this.typeBien1 = typeBien1;
		this.typeBien2 = typeBien2;
		this.surfaceBien = surfaceBien;
		this.adresseBien = adresseBien;
		this.equipementsBien = equipementsBien;
		this.dateEffetDuContrat = dateEffetDuContrat;
		this.depotGarantie = depotGarantie;
		this.montantLoyer = montantLoyer;
		this.montantCharges = montantCharges;
		this.montantLoyerEnLettres = montantLoyerEnLettres;
		this.montantChargesEnLettres = montantChargesEnLettres;
		this.regimeJuridiqueImmeuble = regimeJuridiqueImmeuble;
		this.periodeConstruction = periodeConstruction;
		this.nbPiecesHabitables = nbPiecesHabitables;
		this.autresPartiesLogementElementsEq = autresPartiesLogementElementsEq;
		this.modeProdChauffage = modeProdChauffage;
		this.modeProdEauChaudeSanitaire = modeProdEauChaudeSanitaire;
		this.accessoiresUsagePrivatifLocataire = accessoiresUsagePrivatifLocataire;
		this.accessoiresUsageCommun = accessoiresUsageCommun;
		this.accesoiresInfoCommun = accesoiresInfoCommun;
		this.montantLoyerRef = montantLoyerRef;
		this.montantLoyerMajore = montantLoyerMajore;
		this.complementLoyer = complementLoyer;
	}

}

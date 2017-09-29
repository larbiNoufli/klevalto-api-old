package klevalto.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public  class  InfosMandatPdf extends InfosCommunesPdf {
	
	/**
	 * Nom de l immeuble
	 */
	@Getter
	@Setter
	private String nomImmeuble;
	
	/**
	 * Adresse de l immeuble
	 */
	@Getter
	@Setter
	private String adresseImmeuble;
	
	/**
	 * Periode de construction de l immeuble
	 */
	@Getter
	@Setter
	private String periodeConstructionImmeuble;
	
	/**
	 * Nature du local
	 */
	@Getter
	@Setter
	private String natureLocal;
	/**
	 * Surface de l'immeuble
	 */
	@Getter
	@Setter
	private String surfaceHabitable;
	
	/*
	 * Surface anexxes
	 */
	@Getter
	@Setter
	private String surfaceAnnexes;
	
	/**
	 * Regime fiscale de m immeuble
	 */
	@Getter
	@Setter
	private String regimeFiscal;
	
	
	/**
	 * Numero de la copropriete
	 */
	@Getter
	@Setter
	private String numeroCopropriete;
	
	/**
	 * Numero de mandat
	 */
	@Getter
	@Setter
	private String mandatId;

	
	@Builder
	public InfosMandatPdf(String civiliteBailleur, String prenomBailleur, String nomBailleur, String adresseBailleur,
			String telephoneBailleur, String telephoneMobileBailleur, String mailBailleur, String nationaliteBailleur,
			String situationFamilialeBailleur, String nomImmeuble, String adresseImmeuble,
			String periodeConstructionImmeuble, String natureLocal, String surfaceHabitable, String surfaceAnnexes,
			String regimeFiscal, String numeroCopropriete, String mandatId) {
		super(civiliteBailleur, prenomBailleur, nomBailleur, adresseBailleur, telephoneBailleur,
				telephoneMobileBailleur, mailBailleur, nationaliteBailleur, situationFamilialeBailleur);
		this.nomImmeuble = nomImmeuble;
		this.adresseImmeuble = adresseImmeuble;
		this.periodeConstructionImmeuble = periodeConstructionImmeuble;
		this.natureLocal = natureLocal;
		this.surfaceHabitable = surfaceHabitable;
		this.surfaceAnnexes = surfaceAnnexes;
		this.regimeFiscal = regimeFiscal;
		this.numeroCopropriete = numeroCopropriete;
		this.mandatId = mandatId;
	}


}

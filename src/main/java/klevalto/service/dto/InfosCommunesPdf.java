package klevalto.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
public  class  InfosCommunesPdf {
	
	// Le Bailleur
	/**
	 * Civilite Bailleur
	 */
	@Getter
	@Setter
	private String civiliteBailleur;
	/**
	 * Prenom du bailleur
	 */
	@Getter
	@Setter
	private String prenomBailleur;
	
	
	/**
	 * Nom du bailleur
	 */
	@Getter
	@Setter
	private String nomBailleur;
	
	/**
	 * Adresse du bailleur
	 */
	@Getter
	@Setter
	private String adresseBailleur;
	
	/*
	 * Telephone fixe bailleur
	 */
	@Getter
	@Setter
	private String telephoneBailleur;
	
	
	/*
	 * Telephone mobile bailleur
	 */
	@Getter
	@Setter
	private String telephoneMobileBailleur;
	
	/**
	 * Adresse mail du bailleur
	 */
	@Getter
	@Setter
	private String mailBailleur;
	/*
	 * Nationalite du bailleur
	 */
	@Getter
	@Setter
	private String nationaliteBailleur;
	
	/**
	 * situationFamilialeBailleur
	 */
	@Getter
	@Setter
	private String situationFamilialeBailleur;

	public InfosCommunesPdf(String civiliteBailleur, String prenomBailleur, String nomBailleur, String adresseBailleur,
			String telephoneBailleur, String telephoneMobileBailleur, String mailBailleur, String nationaliteBailleur,
			String situationFamilialeBailleur) {
		super();
		this.civiliteBailleur = civiliteBailleur;
		this.prenomBailleur = prenomBailleur;
		this.nomBailleur = nomBailleur;
		this.adresseBailleur = adresseBailleur;
		this.telephoneBailleur = telephoneBailleur;
		this.telephoneMobileBailleur = telephoneMobileBailleur;
		this.mailBailleur = mailBailleur;
		this.nationaliteBailleur = nationaliteBailleur;
		this.situationFamilialeBailleur = situationFamilialeBailleur;
	}
	
	

	

}

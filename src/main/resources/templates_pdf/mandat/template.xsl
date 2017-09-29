<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
    xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
<xsl:template match="sergictemplate">
    <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format" >
      <fo:layout-master-set>
        <fo:simple-page-master master-name="simpleA4" page-height="29.7cm" page-width="21cm" margin-top="2cm" margin-bottom="1cm" margin-left="2cm" margin-right="2cm">
          
          <fo:region-body region-name="xsl-region-body" margin-top="2cm" />
          <fo:region-before region-name="xsl-region-before"/>
          <fo:region-after region-name="xsl-region-after"/>
        </fo:simple-page-master>
      </fo:layout-master-set>
      <fo:page-sequence master-reference="simpleA4">
	      <fo:static-content flow-name="xsl-region-before">
	      	<fo:table space-after="60mm" >
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell>
							<fo:block>
								<fo:instream-foreign-object>
									<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
										width="114" height="34" viewBox="0 0 147.685 48.472" fill="#58585a">
										<defs>
											<linearGradient id="a" x1="131.062" y1="45.259"
												x2="131.062" y2="31.827" gradientUnits="userSpaceOnUse">
												<stop offset="0" stop-color="#b0c700" />
												<stop offset="1" stop-color="#6aaf23" />
											</linearGradient>
											<linearGradient id="b" x1="131.062" y1="45.259"
												x2="131.062" y2="29.891" gradientUnits="userSpaceOnUse">
												<stop offset="0" stop-color="#b0b2b3" />
												<stop offset="0.7" stop-color="#58585a" />
											</linearGradient>
										</defs>
										<title>Klévalto</title>
										<polygon
											points="13.688 8.409 6.12 17.145 6.12 0 0 0 0 31.393 6.12 31.393 6.12 24.105 9.063 21.069 14.108 31.393 20.928 31.393 13.407 16.864 21.115 8.409 13.688 8.409" />
										<rect x="22.33" width="6.073" height="31.393" />
										<path
											d="M49.448,11.212Q46.715,7.8,41.39,7.8a12.7,12.7,0,0,0-4.718.817,9.386,9.386,0,0,0-3.48,2.359,10.384,10.384,0,0,0-2.172,3.691,14.577,14.577,0,0,0-.747,4.812Q30.271,32,41.763,32a10.589,10.589,0,0,0,6.353-1.915,10.08,10.08,0,0,0,3.737-5.466h-6.26a3.066,3.066,0,0,1-1.472,1.892,5.083,5.083,0,0,1-2.64.677,4.623,4.623,0,0,1-3.62-1.541,6.561,6.561,0,0,1-1.518-4.158H52.181Q52.181,14.622,49.448,11.212ZM36.531,17.658a5.679,5.679,0,0,1,1.518-3.714A4.31,4.31,0,0,1,41.2,12.613a5.064,5.064,0,0,1,1.729.3,4.189,4.189,0,0,1,1.5.935,4.647,4.647,0,0,1,1.051,1.588,6.407,6.407,0,0,1,.444,2.219Z" />
										<polygon
											points="68.254 8.692 63.395 23.875 58.584 8.692 51.81 8.692 60.032 31.676 66.385 31.676 74.654 8.692 68.254 8.692" />
										<path
											d="M92.1,10.444a7.42,7.42,0,0,0-3.037-1.752,15.2,15.2,0,0,0-4.6-.607,16.193,16.193,0,0,0-4.672.584,8.094,8.094,0,0,0-3.106,1.658A6.266,6.266,0,0,0,74.957,12.9a10.407,10.407,0,0,0-.584,3.27h5.653a4.377,4.377,0,0,1,1.144-2.476,4.085,4.085,0,0,1,3.013-.934,5.468,5.468,0,0,1,2.826.631,1.951,1.951,0,0,1,1.051,1.752,1.96,1.96,0,0,1-.794,1.752,8.137,8.137,0,0,1-3.083.864q-2.289.327-4.228.724a10.578,10.578,0,0,0-3.34,1.238,5.917,5.917,0,0,0-2.2,2.313,8.328,8.328,0,0,0-.794,3.947,5.994,5.994,0,0,0,1.845,4.555q1.845,1.752,5.816,1.752A10.836,10.836,0,0,0,85.4,31.56a7.511,7.511,0,0,0,2.85-1.939,9.3,9.3,0,0,0,.374,2.056h5.652V17.148a13.369,13.369,0,0,0-.514-3.854A6.686,6.686,0,0,0,92.1,10.444Zm-3.948,12.5a4.372,4.372,0,0,1-.444,1.939,5.295,5.295,0,0,1-2.9,2.663,5.852,5.852,0,0,1-2.172.4q-2.943,0-2.943-2.569a2.776,2.776,0,0,1,.981-2.289,8.712,8.712,0,0,1,3.6-1.308,24.752,24.752,0,0,0,2.569-.607,3.285,3.285,0,0,0,1.355-.747Z" />
										<rect x="96.703" y="0.283" width="6.073" height="31.393" />
										<path
											d="M130.956,8.02a12.122,12.122,0,0,0-11.89,14.48c-.9,2.12-2.369,4.505-4.424,4.505a3.021,3.021,0,0,1-2.686-1.191,5.943,5.943,0,0,1-.818-3.387V12.99h5.025v-4.3h-5.025V2.479h-5.98V23.034a12.066,12.066,0,0,0,.514,3.55,7.629,7.629,0,0,0,1.658,2.943,8.118,8.118,0,0,0,2.92,2.009,11.07,11.07,0,0,0,4.3.747c3.623,0,5.784-3.218,6.744-4.825A12.132,12.132,0,1,0,130.956,8.02Zm.025,19.115a6.978,6.978,0,1,1,6.969-6.987A6.978,6.978,0,0,1,130.981,27.135Z" />
										<polygon
											points="35.002 7.239 34.392 3.843 47.252 1.325 48.201 6.402 35.002 7.239" />
										<path
											d="M145.5,45.259a20.476,20.476,0,0,0-4.929-12.337,12.97,12.97,0,0,1-3.716,2.239,16.077,16.077,0,0,1-5.988,1.07,16.419,16.419,0,0,1-6.19-1.1,13.423,13.423,0,0,1-3.365-1.956,20.384,20.384,0,0,0-4.7,12.081Z"
											fill="url(#a)" />
										<path
											d="M116.62,45.259a20.39,20.39,0,0,1,4.7-12.084,13.423,13.423,0,0,0,3.365,1.956,16.419,16.419,0,0,0,6.19,1.1,16.077,16.077,0,0,0,5.988-1.07,12.97,12.97,0,0,0,3.716-2.239A20.476,20.476,0,0,1,145.5,45.258l2.181,0c0-.413-.077-9.277-7.069-15.368-.115.133-.222.273-.343.4a11.429,11.429,0,0,1-4.079,2.715,14.143,14.143,0,0,1-5.244.942,14.413,14.413,0,0,1-5.412-.968,11.625,11.625,0,0,1-4.1-2.736c-.05-.052-.092-.112-.141-.165-6.781,6.084-6.861,14.77-6.86,15.179Z"
											fill="url(#b)" />
										<g fill="#1d1d1b">
											<polygon
												points="2.342 36.18 0 44.257 2.042 44.255 4.384 36.177 2.342 36.18"
												fill="#009ddf" />
											<path
												d="M11.666,39.2c-.99-.156-1.578-.3-1.578-.72,0-.3.385-.643,1.329-.644.758,0,1.206.3,1.331.672l2.12,0a3.227,3.227,0,0,0-3.453-2.519c-2.3,0-3.4,1.322-3.4,2.591,0,1.8,1.905,2.192,3.328,2.411,1.392.217,1.67.406,1.671.86,0,.47-.617.722-1.436.723a1.8,1.8,0,0,1-1.7-.939l-2.073,0c.25,1.739,1.752,2.821,3.779,2.818s3.494-1.135,3.492-2.734c0-1.756-1.427-2.193-3.406-2.518" />
											<path
												d="M17.342,39.334a2.043,2.043,0,0,1,1.964-1.54,2,2,0,0,1,2,1.534Zm1.975-3.358a4.236,4.236,0,0,0,.011,8.467,3.781,3.781,0,0,0,3.848-2.765L21,41.68a1.845,1.845,0,0,1-1.684.943,2.033,2.033,0,0,1-2-1.6l6.018-.008c.289-3.2-1.661-5.045-4.014-5.042" />
											<path
												d="M28.119,36.011c-2.645-.325-4.2,1.276-4.2,3.564l.006,4.673,2.042,0-.006-4.673a1.641,1.641,0,0,1,2.162-1.617Z" />
											<path
												d="M32.164,42.5a2.146,2.146,0,0,1-2.044-2.271A2.053,2.053,0,1,1,32.164,42.5m2.128-5.474a3.313,3.313,0,0,0-2.446-1.032,3.99,3.99,0,0,0-3.783,4.223,3.985,3.985,0,0,0,3.794,4.2,3.325,3.325,0,0,0,2.351-.944v.392c0,1.443-.647,2.4-2.085,2.4a1.756,1.756,0,0,1-1.625-.891l-2.243,0a3.724,3.724,0,0,0,3.87,2.818c2.552,0,4.128-1.685,4.124-4.364l-.01-7.667-1.949,0Z" />
											<path
												d="M38.294,33.082a1.029,1.029,0,0,0-1.036,1.036l0,1.16,2.056,0,0-1.16a1.025,1.025,0,0,0-1.021-1.034" />
											<rect x="37.281" y="36.154" width="2.042" height="8.075"
												transform="translate(-0.052 0.05) rotate(-0.075)" />
											<path
												d="M47.956,41.488l-2.182,0a1.35,1.35,0,0,1-.083.148h0a1.88,1.88,0,0,1-1.586.889,2.2,2.2,0,0,1-2.044-2.318,2.225,2.225,0,0,1,2.038-2.37,1.9,1.9,0,0,1,1.568.869h0a1.591,1.591,0,0,1,.088.148l2.181,0c-.013-.051-.029-.1-.043-.148h0a3.755,3.755,0,0,0-3.8-2.764,4.235,4.235,0,0,0,.011,8.467,3.748,3.748,0,0,0,3.8-2.774h0C47.926,41.587,47.942,41.539,47.956,41.488Z" />
										</g>
									</svg>
		
								</fo:instream-foreign-object>
							</fo:block>
		
						</fo:table-cell>
												
							
						<fo:table-cell >
						
							<fo:block color="#7ABC31"  font-size="10pt" text-align="left">
								www.klevalto.com | contact@klevalto.fr 
								</fo:block>
								<fo:block font-size="10pt">
								6, rue Konrad Adenauer 59 447 Wasquehal Cedex
								</fo:block>
								<fo:block font-size="6pt">
								CS : Lille Métropole B 790 608 715 | Code APE : 6832A | SIRET : 790 608 715 00013 
								</fo:block>
						
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
		
			</fo:table>
	      </fo:static-content>

	<fo:static-content flow-name="xsl-region-after">
		<fo:block text-align="center" font-size="11pt" color="#000000" >
			<fo:inline >
				<fo:page-number />
			</fo:inline>
		</fo:block>
	</fo:static-content>
      
      <fo:flow flow-name="xsl-region-body" >
 
      
      
		        
		

		  
		  <fo:block  font-size="8pt" space-after="5mm">
		  Inscription au registre des mandats sous le n° {MANDAT_ID}
		  </fo:block>
		  
		  	  <fo:block color="#7ABC31"  font-size="15.5pt" font-weight="bold" space-after="5mm" text-align="center">
		  MANDAT DE GESTION - Klévalto
		  </fo:block>
		  
		  <fo:table table-layout="fixed" space-after="5mm" border="1px solid black">
				<fo:table-body>
					<fo:table-row font-size="10pt">
						<fo:table-cell display-align="center">
							<fo:block>
							Dénomination sociale : KLEVALTO, SASU Société par actions simplifiée à associé unique
							</fo:block>
							<fo:block>
							Président de la société : Sergic Invest représentée par Etienne Dequirez
							</fo:block>
							<fo:block>
							Capital social : 250 000 Euros
							</fo:block>
							<fo:block>
							RCS : Lille Métropole  B 790 608 715
							</fo:block>
							<fo:block>
							N° TVA intracommunautaire : FR 05 790 608 715
							</fo:block>
							<fo:block>
							Titre professionnel : Agent immobilier
							</fo:block>
							<fo:block>
							Carte professionnelle : CPI 5906 2017 000 020 174
							</fo:block>
							<fo:block>
							Code APE : 6832A
							</fo:block>
							<fo:block>
							Activités : Gestion Immobilière - Membre de l’UNIS, soumis au code de déontologie des professions immobilières
							</fo:block>
							<fo:block>
							Siège social : 6 rue Konrad Adenauer - 59290 Wasquehal
							</fo:block>
							<fo:block>
							N° de téléphone : 03.20.12.50.00
							</fo:block>
							<fo:block>
							N° de télécopie : 03.20.40.21.47
							</fo:block>
							<fo:block>
							Adresse électronique : contact@klevalto.fr
							</fo:block>
							<fo:block>
							Site (ci-après « le site ») : www.klevalto.com  
							</fo:block>
							<fo:block>
							Caisse de garantie : Compagnie Européenne de Garanties et de Cautions (anciennement SOCAMAB) – 16 Rue Hoche – Tour Kupka B – TSA 39999 – 92919 La Défense Cedex
							</fo:block>
							<fo:block>
							Montant de la garantie : 110 000 Euros
							</fo:block>
							<fo:block>
							Couverture géographique de la garantie : France métropolitaine
							</fo:block>
							<fo:block>
							Assurance responsabilité civile professionnelle : GENERALI IARD – 7, boulevard Haussman 75009 PARIS
							</fo:block>
							<fo:block>
							Couverture géographique de la garantie: France Métropolitaine et Collectivités d’Outre Mer
							</fo:block>
							
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
		  </fo:table>

	
			<fo:block font-size="10pt"  font-weight="bold" space-after="5mm">
		   Préambule :
		   </fo:block>
		   <fo:block font-size="10pt"  font-weight="bold" space-after="1cm">
		   Le présent contrat est un mandat de gestion locative dématérialisé nécessitant l’utilisation d’une application propre aux supports mobiles (Smartphone et tablettes informatiques). La dématérialisation (zéro papier) nécessite entre autre un espace sécurisé tant pour le mandant que pour le locataire, un outil de messagerie dédié à la relation locataire-bailleur, et une assistance en ligne.
Aussi, les échanges, tant avec le mandant qu’avec le locataire, se feront en priorité de manière dématérialisée.

		   </fo:block>

		   <fo:block font-size="14pt" font-weight="bold"  space-after="5mm">
		   1 – DESIGNATION DES PARTIES - LE PRESENT MANDAT EST SIGNE ENTRE :
		   </fo:block>

		   <fo:block font-size="10pt" >
		   {CIVILITE_BAILLEUR} {NOM_BAILLEUR} {PRENOM_BAILLEUR}
		   </fo:block>
		   <fo:block font-size="10pt">
		   Demeurant : {ADRESSE_BAILLEUR}
		   </fo:block>
		   <fo:block font-size="10pt">
		   Fixe Perso : {TELEPHONE}          Portable Perso : {TELEPHONE_PORTABLE}          email : {EMAIL}
		   </fo:block>
		   
		   <fo:block font-size="10pt">
		   Nationalité : {NATIONALITE}                    Situation de famille : {SITUATION_FAMILIALE}
		   </fo:block>
		   
		   <fo:block font-size="10pt" space-after="5mm">
		   Propriétaire(s)  des biens et droits immobiliers énoncés dans le présent mandat, désigné comme
		   <fo:inline font-weight="bold"> le mandant
		   </fo:inline> dans la suite des présentes,
		   </fo:block>
		   
		   <fo:block font-size="10pt" font-weight="bold">
		   ET :
		   </fo:block>
		   <fo:block font-size="10pt" space-after="5mm">
		   <fo:inline font-weight="bold">KLEVALTO SASU</fo:inline>, au capital de 250 000 € dont le siège social situé au 6 rue Konrad Adenauer, 59290 Wasquehal désignée comme
		   <fo:inline font-weight="bold">le mandataire</fo:inline> dans la suite des présentes.
		   </fo:block>
		   
		   <fo:block font-size="10pt" space-after="5mm">
		   Le mandant reconnaît avoir été informé préalablement à la souscription du présent contrat 
		   au moyen des informations contenues dans les documents commerciaux du mandataire et/ou les 
		   informations figurant sur le site internet : www.klevalto.com  des caractéristiques essentielles 
		   du mandat, de son prix, des conditions générales de vente et d’exercice de son mandat par 
		   le mandataire, ainsi que de ses coordonnées. En outre il reconnaît avoir pris connaissance 
		   des conditions du présent mandat dont il a reçu un exemplaire.
		   </fo:block>
		   
		   <fo:block font-size="10pt" space-after="5mm">
		   Le propriétaire, ci-après désigné « le mandant ou le propriétaire », donne tout pouvoir à 
		   KLEVALTO, ci-après désigné « le mandataire » pour gérer le ou les logements décrits ci-dessous 
		   dans le respect des dispositions contractuelles ci-dessous définies. Le mandataire est 
		   tenu d’une obligation de moyen dans la gestion financière du bien.
		   </fo:block>
		   
		   <fo:block font-size="14pt" font-weight="bold"  space-after="5mm">
		   2 – DESIGNATION DES BIENS :
		   </fo:block>
		   
		   <fo:block font-size="10pt" font-weight="bold">
			IMMEUBLE
		   </fo:block>
		  
		   <fo:block font-size="10pt" font-weight="bold">
		   •   <fo:inline font-weight="normal">Nom de l'immeuble :   {NOM_IMMEUBLE}</fo:inline>
		   </fo:block>
		   <fo:block font-size="10pt" font-weight="bold">
		   •   <fo:inline font-weight="normal">Adresse :   {ADRESSE_IMMEUBLE}</fo:inline>
		   </fo:block>
		   <fo:block font-size="10pt" font-weight="bold" space-after="5mm">
		   •   <fo:inline font-weight="normal">Période de construction :   {PERIODE_CONSTRUCTION}</fo:inline>
		   </fo:block>
		   
		    <fo:block font-size="10pt" font-weight="bold">
			LOGEMENT
		    </fo:block>
		   
		   <fo:block font-size="10pt" font-weight="bold">
		   •   <fo:inline font-weight="normal">Nature du local :   {NATURE_LOCAL}  </fo:inline>
		   </fo:block>
		   
		   <fo:block font-size="10pt" font-weight="bold">
		   •   <fo:inline font-weight="normal">Superficie habitable {SURFACE_HABITABLE} m²</fo:inline>
		   </fo:block>
		   
		   <fo:block font-size="10pt" font-weight="bold">
		   •   <fo:inline font-weight="normal">Superficie des annexes {SURFACE_ANNEXES} m²</fo:inline>
		   </fo:block>
		   
		   <fo:block font-size="10pt" font-weight="bold">
		   •   <fo:inline font-weight="normal">Régime fiscal : {REGIME_FISCAL}</fo:inline>
		   </fo:block>
		   <fo:block font-size="10pt" font-weight="bold" space-after="5mm">
		   •   <fo:inline font-weight="normal">N° de copropriété : {NUMERO_COPROPRIETE}</fo:inline>
		   </fo:block>
		   
		   <fo:block font-size="14pt" font-weight="bold"  space-after="5mm">
		   3 – DUREE DU MANDAT :
		   </fo:block>
		   
		   <fo:block font-size="10pt" font-weight="bold"  space-after="5mm">
		   3.1 – DUREE DU MANDAT :
		   </fo:block>
		   
		   <fo:block font-size="10pt" space-after="5mm">
		   Le présent mandat est consenti et accepté pour une durée de trois ans qui court à compter de l’acceptation 
		   du mandat par le mandataire. A défaut de résiliation par l’une quelconque des parties par lettre 
		   recommandée avec accusé de réception un mois avant son échéance, le présent contrat se renouvelle
		   par tacite reconduction et pour une période de trois ans renouvelable,
		   la durée totale du mandat ne pouvant excéder 30 ans.
		   </fo:block>
		   
		   <fo:block font-size="10pt" space-after="5mm">
		   A la fin du contrat, le mandataire est alors dégagé de toute obligation de suivi ou de conseil à 
		   l’égard des administrations fiscales comme à l’égard du locataire, notamment en ce qui concerne 
		   la gestion des charges locatives. 
		   </fo:block>
		   
		   <fo:block font-size="10pt" space-after="5mm">
		   Par dérogation aux dispositions de l’article 2003 du Code Civil, le décès du mandant n’emporte pas 
		   résiliation de plein droit du mandat. Les ayants droit du défunt resteront tenus par l’exécution de 
		   la convention, fussent-ils mineurs ou incapables. Les héritiers devront, à première demande, fournir 
		   un acte de notoriété ainsi que les coordonnées du notaire auprès duquel la succession aura été ouverte.
		   </fo:block>
		   
		   <fo:block font-size="10pt" font-weight="bold" space-after="5mm" >
		   3.2 - OFFRE SANS ENGAGEMENT PENDANT TOUTE LA DUREE DU MANDAT :
		   </fo:block>
		   
		   <fo:block font-size="10pt" space-after="5mm" >
		   Le mandant pourra dénoncer le présent mandat, à tout moment, par courrier recommandé avec demande d’avis de 
		   réception. Cette résiliation prendra effet un mois après la réception du courrier recommandé. Les honoraires 
		   déjà versés au titre du mois en cours resteront acquis au mandataire.
		   </fo:block>
		   
		   <fo:block font-size="10pt" space-after="5mm" >
		   Chacune des parties pourra résilier le présent mandat à tout moment dans le cas où l’autre partie 
		   ne remplirait pas ses obligations et/ou ne respecterait pas les conditions d’utilisation du site, 
		   à charge pour elle de lui notifier avec demande d’avis de réception et en respectant 
		   un préavis d’un mois pour le mandataire ; aucun préavis ne sera requis pour le mandant. 
		   Si le bien objet du mandat ne respecte pas les critères de décence, 
		   le mandataire se réserve le droit de résilier le présent mandat dans les conditions du présent article.
		   </fo:block>
		   
		   <fo:block font-size="10pt" font-weight="bold"  space-after="5mm">
		   3.3 - INFORMATION CONCERNANT L'EXERCICE DU DROIT DE RETRACTATION :
		   </fo:block>
		   
		   <fo:block font-size="10pt" space-after="5mm" >
		   Le mandant est informé qu’il dispose du droit de se rétracter sans donner de motif dans un 
		   délai de quatorze jours à compter de la signature des présentes. Pour exercer son droit de rétractation, 
		   le mandant doit notifier sa décision au mandataire en utilisant le bordereau de rétractation mis à sa 
		   disposition sur le site www.klevalto.com   ou par toute autre déclaration dénuée d’ambiguïté, qu’elle 
		   soit faite de façon scripturale ou électronique. 
		   </fo:block>
		   
		   <fo:block font-size="10pt" space-after="5mm"  font-weight="bold" >
		   Sans préjudice de son éventuel droit de rétractation, le mandant demande expressément au 
		   mandataire de démarrer sans délai l’exécution du mandat dès sa date d’effet, et il accepte en 
		   conséquence de payer les honoraires dus pour la période considérée, soit à compter de la date 
		   de prise d’effet mentionnée au mandat.
		   </fo:block>
		   
		   <fo:block font-size="10pt" font-weight="bold"  space-after="5mm">
		   3.4 - RAPPEL DES DISPOSITIONS DU CODE DE LA CONSOMMATION :
		   </fo:block>
		   
		   <fo:block font-size="10pt" space-after="5mm">
		   En application des dispositions de l’art. L 215-1 du code de la consommation, le mandataire 
		   informera le mandant par écrit, au plus tôt trois mois et au plus tard un mois avant le terme 
		   du mandat, de la possibilité pour lui de ne pas reconduire le mandat.
		   </fo:block>
		   <fo:block font-size="10pt">
		   Reproduction de l’art. Article L 215-1 modifié par LOI n°2014-344 du 17 mars 2014 - art. 35 
		   </fo:block>
		   <fo:block font-size="10pt" font-style="italic" space-after="5mm" >
		   adressée conformément aux dispositions du premier alinéa, le consommateur peut mettre gratuitement 
		   un terme au contrat, à tout moment à compter de la date de reconduction. Les avances effectuées 
		   après la dernière date de reconduction ou, s’agissant des contrats à durée indéterminée, après 
		   la date de transformation du contrat initial à durée déterminée, sont dans ce cas remboursées 
		   dans un délai de trente jours à compter de la date de résiliation, déduction faite des sommes 
		   correspondant, jusqu’à celle-ci, à l’exécution du contrat. Les dispositions du présent 
		   article s’appliquent sans préjudice de celles qui soumettent légalement certains contrats 
		   à des règles particulières en ce qui concerne l’information du consommateur.
		   </fo:block>
			
			
			<fo:block font-size="14pt" font-weight="bold"  space-after="5mm">
			4 – ESTIMATION DU LOYER :
			</fo:block>
			
			<fo:block font-size="10pt" space-after="5mm" >
			Le loyer et les provisions pour charges sont déterminés par le mandant <fo:inline font-weight="bold">
			dans le respect des dispositions législatives et/ou	règlementaires en vigueur</fo:inline>.
			</fo:block>
			<fo:block font-size="10pt" space-after="5mm">
			Loyer du logement : {MONTANT_LOYER} Euros
			</fo:block>
			<fo:block font-size="10pt" space-after="5mm">
			Provision pour charges : {MONTANT_CHARGES} Euros
			</fo:block>
			
			<fo:block font-size="14pt" font-weight="bold"  space-after="5mm">
			5 – REVUE DE CONTRAT PERSONNALISEE :
			</fo:block>

			<fo:block font-size="10pt" space-after="5mm">
			Versement des fonds    par virement sur votre compte bancaire
			</fo:block>
			<fo:block font-size="10pt" space-after="5mm">
            Versement  à terme échu, le 5 du mois suivant
			</fo:block>

			<fo:block font-size="14pt" font-weight="bold"  space-after="5mm">
			6 – HONORAIRES :
			</fo:block>

			<fo:block font-size="10pt" space-after="5mm">
			Le mandataire aura droit pour toutes ses prestations de gestion couvertes par 
			le présent mandat à des honoraires fixés en un forfait mensuel de 29.90 € TTC (selon taux 
			de TVA en vigueur soit actuellement 20 %).
			</fo:block>
			
			<fo:block font-size="10pt" space-after="5mm">
			La rémunération du mandataire sera réglée mensuellement par le mandant, par carte bancaire ou 
			mandat SEPA, à chaque date d’anniversaire. A l’issue de chaque période mensuelle, le mandant 
			sera tenu de procéder à un nouveau règlement selon les modalités décrites ci-dessus.
			</fo:block>
			
			<fo:block font-size="10pt" space-after="5mm">
			A défaut de règlement du forfait au terme de chaque échéance mensuelle, le mandataire se 
			réserve le droit de prélever le forfait dû par le mandant sur toutes les sommes encaissées 
			et détenues pour son compte. A défaut, le mandataire se réserve le droit de résilier le présent 
			mandat en respectant un préavis d’un mois par recommandé avec demande d’avis de réception. 
			Cette notification de résiliation vaudra mise en demeure.
			</fo:block>
			
			<fo:block font-size="10pt" font-weight="bold" space-after="5mm">
			6.1 – OFFRE COMMERCIALE : Trois premiers mois de gestion offerts : 
			</fo:block>
			
			<fo:block font-size="10pt" space-after="5mm">
			Par dérogation à l’article 6, dans le cadre de son offre commerciale offerte aux 150 
			premiers clients (mandant confiant un premier bien en gestion à KLEVALTO) pour toute 
			signature d’un mandat de gestion KLEVALTO, dûment validé par KLEVALTO, les honoraires 
			de gérance facturables durant  la première période trimestrielle de gestion seront fixés à 0 €.
			</fo:block>
			
			<fo:block font-size="10pt" space-after="5mm">
			Il est convenu entre les parties qu’en considération de l’expiration de l’offre commerciale 
			visée ci-dessus, au terme de la première période trimestrielle  de gestion, le mandat se 
			poursuivra au tarif de 29.90 € TTC (selon taux de TVA en vigueur soit actuellement 20 %).
			</fo:block>
			
			<fo:block font-size="10pt" space-after="5mm">
			Cette offre de gratuité temporaire porte exclusivement sur les honoraires de gérance tels 
			que définis à l’article 6 ci-dessus. L’ensemble des prestations annexes disponible depuis 
			l’application, sont exclues de l’offre commerciale et sont facturables à compter de la prise d’effet du mandat.
			Cette offre prend effet dès la prise d’effet du mandat.
			</fo:block>
			
			<fo:block font-size="14pt" font-weight="bold"  space-after="5mm">
			7 – OBJET DU MANDAT :
			</fo:block>
			
			
			<fo:block font-size="10pt"  font-weight="bold" space-after="5mm">
			7.1 - Missions incluses dans le forfait de gestion
			</fo:block>
			
		   <fo:block font-size="10pt" space-after="5mm">
		   Dans le cadre de son mandat KLEVALTO, le mandataire s’engage à réaliser pour le compte du mandant :
		   </fo:block>
			
			<fo:block font-size="10pt"  font-weight="bold"  space-after="5mm">
			A – Gestion Financière du logement :
			</fo:block>
			
		   <fo:block font-size="10pt" space-after="5mm">
			Le mandataire procède auprès du ou des locataires et pour le compte du mandant aux appels de loyers, charges, régularisation de charges et toutes autres sommes accessoires au bail.
	   	</fo:block>			
			
		   <fo:block font-size="10pt" space-after="5mm">
		   Le mandant autorise le mandataire à recevoir pour son compte les sommes représentant les loyers, 
		   charges, indemnités d’occupation, dépôts de garantie, subventions, avances sur travaux et plus 
		   généralement toute somme d’argent dont la perception est la conséquence directe ou indirecte 
		   du ou des biens du mandant.
		   </fo:block>	
		   
		   <fo:block font-size="10pt" space-after="5mm">
			Après encaissement par le mandataire, le dépôt de garantie est restitué au mandant avec le premier revenu locatif.Après encaissement par le mandataire, le dépôt de garantie est restitué au mandant avec le premier revenu locatif.
		   </fo:block>			

		   <fo:block font-size="10pt" space-after="5mm">
		   Il est convenu entre les parties que les éventuelles aides au logement dont pourrait bénéficier le locataire seront perçues prioritairement par le mandataire ou par le locataire lui-même. A défaut et si le versement des aides au logement s’effectue au profit du mandant, ce dernier s’oblige à informer mensuellement le mandataire du montant perçu.
		   </fo:block>
		   		
		   <fo:block font-size="10pt" space-after="5mm">
		   Le solde antérieur de gestion, arrêté à la date de prise d’effet du présent mandat, n’est pas opposable au mandataire. 
		   </fo:block>
		   
		   <fo:block font-size="10pt" space-after="5mm">
		   Le mandant renonce à toute rémunération des fonds détenus pour son compte, et ce quand bien même une rémunération serait servie au mandataire par la banque dépositaire desdits fonds.
		   </fo:block>
		   
		   <fo:block font-size="10pt" space-after="5mm">
		   Le mandataire rend compte de sa gestion une fois par mois, par l’envoi au mandant d’un relevé détaillé des recettes et dépenses via l’Application.
		   </fo:block>

		   <fo:block font-size="10pt"   font-weight="bold" space-after="5mm">
			B – Garantie des impayés du locataire :
			</fo:block>
			
			<fo:block font-size="10pt" space-after="5mm">
		   Sous réserve que son locataire soit éligible au dispositif, pendant toute la durée du mandat, le propriétaire perçoit le montant du loyer et des charges locatives récupérables définis au bail, même en cas d’impayé du locataire, selon la périodicité et dans les conditions définies ci-dessous. Dans tous les cas, le propriétaire conserve à sa charge les impôts et taxes lui incombant habituellement, les charges de copropriété ainsi que toutes les dépenses de fonctionnement habituellement supportées par les propriétaires.
		   </fo:block>	
			
			<fo:block font-size="10pt"  font-weight="bold" space-after="5mm">
			B.1 - Loyers et charges impayés
			</fo:block>
		   
		   <fo:block font-size="10pt" space-after="5mm">
		   Sous réserve que son locataire soit éligible au dispositif, en cas d’impayés, le mandataire procède à une avance de fonds sur laquelle s’imputeront les sommes éventuellement recouvrées sur le locataire. Cette avance de fonds couvre le montant du loyer et des charges locatives pendant la durée du bail et ce jusqu’au départ du locataire ou de son éventuelle expulsion.
		   </fo:block>
		   
		   <fo:block font-size="10pt" space-after="5mm">
		   L’avance de fonds est versée à terme échu et au plus tard le 5 du mois qui suit le mois pour lequel elle est due, par virement, sans période de franchise ni délai de carence.
		   </fo:block>
		   
		   <fo:block font-size="10pt" space-after="5mm">
		   Le mandataire intervient à concurrence de 7 000 Euros par location. Le plafond de la garantie s’épuisera au fur et à mesure des indemnités versées par le mandataire.
		   </fo:block>
		   
		   <fo:block font-size="10pt" space-after="5mm">
		   Ce plafond se calcule en fonction des indemnités versées par le mandataire, indépendamment des éventuels règlements des arriérés par le locataire. Dans le cas où le locataire défaillant reprendrait un versement régulier de ses loyers, il est convenu que le plafond de 7 000 Euros ne se reconstitue pas d’autant.
		   </fo:block>
		   
		   <fo:block font-size="10pt" space-after="5mm">
		   Le mandataire assure seul et pour le compte du bailleur la direction de la procédure de recouvrement et de résiliation du bail. Le propriétaire s’engage à fournir sur simple requête du mandataire toutes les pièces nécessaires au succès de ces procédures.
		   </fo:block>
		   
		   <fo:block font-size="10pt" space-after="5mm">
		   Le mandataire assure seul et pour le compte du bailleur la direction de la procédure de recouvrement et de résiliation du bail. Le propriétaire s’engage à fournir sur simple requête du mandataire toutes les pièces nécessaires au succès de ces procédures.
		   </fo:block>
		   <fo:block font-size="10pt" space-after="5mm">
		   Les sommes recouvrées en cours de mandat seront remboursées au mandataire dès lors que ce dernier aura effectivement avancé au propriétaire les loyers et charges correspondants. De même, en cas de résiliation du mandat ou de vente du bien, le mandataire est subrogé dans les droits du propriétaire pour le recouvrement des loyers et charges impayés dont il aura fait l’avance.
		   </fo:block>	
		   <fo:block font-size="10pt" space-after="5mm">
		   L’avance de fonds au propriétaire est suspendue lorsque le locataire dispose d’un motif légitime pour s’opposer au règlement du loyer et/ou des charges tenant à une inexécution contractuelle substantielle du propriétaire ou d’une décision de justice suspendant, pour quelque raison que ce soit, le règlement de tout ou partie du loyer ou des charges, et ce jusqu’à ce que le litige soit résolu entre les parties.
		   </fo:block>	
		   
		   
		   <fo:block font-size="10pt" space-after="5mm">
		   Le mandant s’engage à informer sans délai le mandataire en cas de versement des arriérés par le locataire directement entre ses mains. Dans ce cas, il s’engage également à rembourser, sans délai, au mandataire les sommes perçues en indemnisation des dits arriérés.
		   </fo:block>
		   
		   <fo:block font-size="10pt" space-after="5mm">
		   Le mandataire est informé que tout versement du locataire s’imputera sur la dette la plus ancienne.
		   </fo:block>

			<fo:block font-size="10pt"  font-weight="bold" space-after="5mm">
			B.2 - Prise en charge des frais de contentieux et de recouvrement :
			</fo:block>
		   <fo:block font-size="10pt" space-after="5mm">
		   Sous réserver de l’éligibilité du locataire au dispositif, le mandataire prendra à sa charge les frais engagés pour le compte du bailleur à l’encontre du locataire défaillant, et de sa caution le cas échéant, ayant son origine dans le contrat de bail.
		   </fo:block>	

		   <fo:block font-size="10pt">
		   Sont garantis les frais engagés dans le cadre de contentieux qui présentent simultanément les caractéristiques suivantes :
		   </fo:block>	
		   
		   <fo:block font-size="10pt" font-weight="bold" >
		   •   <fo:inline font-weight="normal">ils interviennent dans l’un des domaines garantis au présent mandat tels que définis à l’article 7 B,</fo:inline>
		   </fo:block>
		   
		   <fo:block font-size="10pt" font-weight="bold" >
		   •   <fo:inline font-weight="normal">leur caractère conflictuel n’était pas connu du mandant lors de la souscription du présent mandat,</fo:inline>
		   </fo:block>
		   
		   <fo:block font-size="10pt" font-weight="bold" space-after="5mm">
		   •   <fo:inline font-weight="normal">ils surviennent pendant la période de validité du présent mandat,</fo:inline>
		   </fo:block>
		   

		   	<fo:block font-size="10pt"  font-weight="bold">
			Ce qui est pris en charge
			</fo:block>
			
		   <fo:block font-size="10pt" font-weight="bold" >
		   •   <fo:inline font-weight="normal">le coût des actes d’huissier diligentés par le mandataire et choisi par lui pour le compte du bailleur, à l’exception de l’état des lieux,</fo:inline>
		   </fo:block>
		   
		   <fo:block font-size="10pt" font-weight="bold" >
		   •   <fo:inline font-weight="normal">les frais et honoraires d’avocat choisi par le mandataire pour représenter le bailleur dans le cadre des litiges garantis.</fo:inline>
		   </fo:block>
		   
		   <fo:block font-size="10pt" font-weight="bold" space-after="5mm">
		   •   <fo:inline font-weight="normal">les dépenses et autres frais de justice.</fo:inline>
		   </fo:block>
			
			
		    <fo:block font-size="10pt"  font-weight="bold">
			Ce qui n’est pas pris en charge
			</fo:block>
			
		   <fo:block font-size="10pt" font-weight="bold" >
		   •   <fo:inline font-weight="normal">les montants des condamnations prononcées contre le mandant telles que les amendes pénales ou civiles,
dommages et intérêts et condamnations au titre des articles 700 du Code de Procédure Civile.</fo:inline>
		   </fo:block>	
			
		   <fo:block font-size="10pt" font-weight="bold" >
		   •   <fo:inline font-weight="normal">les frais engagés à la seule initiative du mandant sans l’accord express du mandataire.</fo:inline>
		   </fo:block>
			
		   <fo:block font-size="10pt" font-weight="bold" space-after="5mm">
		   •   <fo:inline font-weight="normal">les frais de déménagement et de garde meubles.</fo:inline>
		   </fo:block>
			
			

		   	<fo:block font-size="10pt"  font-weight="bold" space-after="5mm">
			B.3 – Suspension des garanties et obligations :
			</fo:block>
			<fo:block font-size="10pt" space-after="5mm">
			Les avances de fonds prévues aux présentes seront suspendues dans les cas suivants :
			</fo:block>
			
		   	<fo:block font-size="10pt"  font-weight="bold">
			Travaux nécessaires :
			</fo:block>		   
			<fo:block font-size="10pt" >
			A l’occasion d’un changement de locataire ou au cours de la période de gestion du logement, le mandataire peut notifier par LRAR au propriétaire la nécessité d’effectuer des travaux d’aménagement ou de réfection indispensables à la location du logement.
			</fo:block>	
			<fo:block font-size="10pt" space-after="5mm">	
			Les garanties et avances de fonds prévues au présent contrat sont suspendues 8 jours francs après l’envoi de la lettre précitée. En cas d’accord sur les travaux, la suspension prend fin à compter de la réception du chantier par le mandataire.
			</fo:block>	
			<fo:block font-size="10pt" space-after="5mm">	   
			A défaut d’accord sur la réalisation des travaux, le mandataire pourra aller jusqu’à la résiliation du mandat. Dans ce cas, aucune indemnité de résiliation anticipée ne peut lui être demandée.
			</fo:block>	
		   
		    <fo:block font-size="10pt"  font-weight="bold">
			Survenance d'un sinistre :
			</fo:block>	
			<fo:block font-size="10pt" space-after="5mm">	   
			Lorsqu’un sinistre ou un cas de force majeure, de quelque nature qu’il soit, affecte le logement loué ou les parties communes, les garanties et avances prévues aux présentes sont suspendues dès lors que cet événement rend impossible l’occupation décente du logement par le locataire, a fortiori lorsque le logement subit une ruine partielle ou totale.
			</fo:block>	
			
			<fo:block font-size="10pt"  font-weight="bold">
			Etat impropre à sa destination :
			</fo:block>	
			<fo:block font-size="10pt" space-after="5mm">	   
			Les garanties et avances sont également suspendues lorsque l’état du logement ne permet plus sa location conforme à l’usage auquel il est destiné, ou lorsqu’il subit un changement d’environnement sensible.
			</fo:block>	
			
		    <fo:block font-size="10pt"  font-weight="bold" space-after="5mm">
			C – Assistance au quotidien :
			</fo:block>
			
			<fo:block font-size="10pt">
			- Mettre à disposition un outil de messagerie dédié à la relation locataire-bailleur
			</fo:block>
			<fo:block font-size="10pt">
			- Informer sur tout évènement lié au mandat
			</fo:block>
			<fo:block font-size="10pt" space-after="5mm">
			- Proposer une assistance personnalisée en ligne (conseils, FAQ, documents type, réponses personnalisées d’un gestionnaire Klévalto)
			</fo:block>
			
			<fo:block font-size="10pt"  font-weight="bold" space-after="5mm">
			7.2 - Missions facturées en sus du forfait de gestion – honoraires annexes
			</fo:block>
			
			<fo:block font-size="10pt">
			Une rémunération spécifique peut être perçue en contrepartie des prestations particulières réalisées à la demande du mandant.
			</fo:block>
			<fo:block font-size="10pt" space-after="5mm">
			Ces prestations particulières seront accessibles depuis l’application mise à disposition du mandant.
			</fo:block>
			
			<fo:block font-size="10pt" space-after="5mm">
			La liste des prestations particulières est susceptible d’évoluer et d’être modifiée par le mandataire.
			</fo:block>
			
			<fo:block font-size="14pt" font-weight="bold"  space-after="5mm">
			8 – DECLARATIONS ET OBLIGATIONS DU MANDANT :
			</fo:block>
			
			<fo:block font-size="10pt"  font-weight="bold" space-after="5mm">
			8.1 - Déclarations du mandant
			</fo:block>
			
			<fo:block font-size="10pt" space-after="5mm">
			Le mandant déclare qu’il a la pleine et entière capacité de consentir le présent mandat de gestion, qu’il ne fait l’objet d’aucune procédure collective de redressement ou liquidation judiciaire, que le bien ne fait l’objet d’aucune restriction de propriété, d’aucune procédure de saisie immobilière, ni d’aucune restriction de jouissance.
			</fo:block>
			
			<fo:block font-size="10pt" space-after="5mm">
			Le mandant s’engage à respecter les critères de décence et d’habitabilité du logement  conformément à la réglementation en vigueur telle qu’elle ressort du décret n°2002-120 du 30 janvier 2002, des dispositions du Code de la Construction et de l’Habitation (notamment les articles R 111-1 et s.), du Règlement Sanitaire Départemental et du Code de la Santé Publique (notamment l’article L 1331-22).
			</fo:block>
			
			<fo:block font-size="10pt">
			Le mandant reconnaît avoir été informé par le mandataire des dispositions de l’article 3-1 de la loi 
			n° 89-462 du 6 juillet 1989 repris ci-après, portant obligation de préciser dans le bail la surface 
			habitable du bien loué. Après avoir pris connaissance des sanctions prévues par ce texte, il déclare en faire son affaire personnelle et il déclare décharger en conséquence le mandataire de toute responsabilité en cas d’erreur de mesurage.
			</fo:block>
			<fo:block font-size="10pt" space-after="5mm">
			Article 3-1 de la loi n° 89-462 du 6 juillet 1989 <fo:inline font-style="italic">« Lorsque la surface 
			habitable de la chose louée est inférieure de plus d'un vingtième à celle exprimée dans le contrat 
			de location, le bailleur supporte, à la demande du locataire, une diminution du loyer proportionnelle 
			à l'écart constaté. A défaut d'accord entre les parties ou à défaut de réponse du bailleur dans 
			un délai de deux mois à compter de la demande en diminution de loyer, le juge peut être saisi, 
			dans le délai de quatre mois à compter de cette même demande, afin de déterminer, le cas échéant, 
			la diminution de loyer à appliquer. La diminution de loyer acceptée par le bailleur ou prononcée 
			par le juge prend effet à la date de signature du bail. Si la demande en diminution du loyer 
			par le locataire intervient plus de six mois à compter de la prise d'effet du bail, la diminution 
			de loyer acceptée par le bailleur ou prononcée par le juge prend effet à la date de la demande. »
			</fo:inline>
			</fo:block>
			
			<fo:block font-size="10pt"  font-weight="bold" space-after="5mm">
			8.2 - Obligations du mandant
			</fo:block>
			
			<fo:block font-size="10pt" space-after="5mm">
			Pour garantir la bonne exécution des présentes, le Mandant :
			</fo:block>
			<fo:block font-size="10pt">
			•	S’oblige à remettre à la signature du présent mandat, tous les documents utiles permettant au Mandataire d’assurer les missions qui lui sont confiées, à savoir notamment : une attestation notariée ou les 3 premières pages de son titre de propriété, sa pièce d’identité, ses coordonnées bancaires, la copie des baux d’ores et déjà régularisés si les locaux sont occupés, le dossier du locataire, et tout autre documents utiles…
			</fo:block>
			<fo:block font-size="10pt">
			•	S’oblige à régler les honoraires dus au titre du présent mandat selon les modalités fixées dans les articles qui précédent.</fo:block>
			<fo:block font-size="10pt">
			•	S’oblige à informer sans délai le mandataire en cas de versement par le locataire directement entre ses mains, et plus généralement toute somme d’argent dont la perception est la conséquence directe ou indirecte du ou des biens du mandant. 
			</fo:block>
			<fo:block font-size="10pt">
			•	S’oblige à fournir dans les meilleurs délais au Mandataire les éléments relatifs aux régularisations de charges, à défaut, le Mandataire ne serait être tenu responsable d’une régularisation tardive auprès du locataire
			</fo:block>
			<fo:block font-size="10pt">
			•	S’oblige à informer dans les meilleurs délais le Mandataire de la bonne réalisation de travaux d’aménagement ou de réfection du bien objet du présent mandat, notamment quand ces derniers sont indispensables à la location du logement
			</fo:block>
			<fo:block font-size="10pt" space-after="5mm">
			•	S’oblige à contracter une police d’assurance multirisques au profit du ou des logements objets des présentes. Il en avise le mandataire et lui transmet copie par voie électronique de la police souscrite. 
			</fo:block>
			
			
			<fo:block font-size="14pt" font-weight="bold"  space-after="5mm">
			9 – CONVENTION DE PREUVE :
			</fo:block>
			
			<fo:block font-size="10pt" >
			La conclusion et l’exécution du présent mandat sont dématérialisées.
			</fo:block>
			<fo:block font-size="10pt" >
			Par le biais de ses prestataires, KLEVALTO met en œuvre des moyens techniques permettant de respecter les conditions légales en matière de signature et d’écrit électroniques.
			</fo:block>
			<fo:block font-size="10pt" >
			Le mandat fait l’objet d’une signature électronique consistant en un procédé fiable d’identification garantissant son lien avec l’acte auquel elle s’attache. Dans ces conditions, la signature électronique permet d’identifier le signataire, et d’attester son adhésion au contenu de l’acte.
			</fo:block>
			<fo:block font-size="10pt" >
			Le présent mandat signé électroniquement répond aux exigences de l’écrit électronique permettant :
			</fo:block>
			<fo:block font-size="10pt" >
			- D’identifier les personnes dont il émane grâce au procédé de signature électronique utilisé
			</fo:block>
			<fo:block font-size="10pt" space-after="5mm">
			- De s’assurer qu’il est établi et conservé dans des conditions de nature à en garantir l’intégrité (fiabilité lors de la création de l’acte et de son contenu, conservation assurant la sécurité et l’intangibilité de l’acte).
			</fo:block>
			
			<fo:block font-size="10pt" >
			Les notifications entre les parties à l’occasion de l’exécution du mandat s’effectuent également par voie électroniques, dans les mêmes conditions que celles de la signature. Les modalités de ces notifications électroniques sont décrites dans les Conditions générales d’Utilisation du site M-SECURE.
			</fo:block>
			
			<fo:block font-size="10pt" >
			Le mandant et le mandataire conviennent expressément que la signature du présent mandat de gestion par voie électronique, ainsi que l’ensemble des notifications électroniques à l’occasion de son exécution constituent des modalités de preuve valables entre elles, au sens des dispositions de l’article 1316-2 du Code Civil.
			</fo:block>
			
			<fo:block font-size="10pt" space-after="5mm">
			Seules les notifications pour lesquelles le mandat prévoit expressément une autre forme ou modalités de notification, sont exclues de la convention de preuve entre les parties.
			</fo:block>
		   
		   	<fo:block font-size="14pt" font-weight="bold"  space-after="5mm">
			10 – CLAUSE SUBROGATOIRE :
			</fo:block>
			
			<fo:block font-size="10pt" space-after="5mm">
			Le mandataire est subrogé dans tous les droits et obligations du mandant tant en ce qui concerne la gestion du bien immobilier que le recouvrement des loyers ou de toute somme impayée. Notamment, conformément aux articles 1346 et suivants du Code Civil (anciens articles 1249 et suivants du Code Civil), le mandataire est subrogé dans les droits et actions du mandant à l’encontre du locataire et de la caution, et ce à concurrence des sommes dont il a fait l’avance, soit au titre des loyers et charges impayés, soit au titre de tous frais liés aux procédures de recouvrement, soit encore au titre des détériorations immobilières définies à l’article 7 B du mandat.
			</fo:block>
			
			<fo:block font-size="14pt" font-weight="bold"  space-after="5mm">
			11 – THEORIE DE L’IMPREVISION
			</fo:block>
			
			<fo:block font-size="10pt" space-after="5mm">
			En application de l’article 1195 du Code Civil, si un changement de circonstances imprévisible lors de la conclusion du présent mandat rend l’exécution excessivement onéreuse pour une partie qui n’avait pas accepté d’en assumer le risque, celle-ci peut demander une renégociation du contrat à son cocontractant. Elle continue à exécuter ses obligations durant la renégociation.  En cas de refus ou d’échec de la renégociation, les parties conviennent dès à présent de la résolution du présent mandat.
			</fo:block>
			
			
			
			<fo:block font-size="14pt" font-weight="bold"  space-after="5mm">
			12 – ATTRIBUTION DE COMPETENCE :
			</fo:block>
			
			<fo:block font-size="10pt" space-after="5mm">
			Les parties attribuent compétence exclusive aux Tribunaux du ressort du Siège Social de la Société KLEVALTO pour tous les litiges pouvant survenir au sujet des présentes et de leurs suites. 
			</fo:block>
			
			<fo:block font-size="14pt" font-weight="bold"  space-after="5mm">
			13 – DROIT D’ACCES AUX DONNES INFORMATIQUES :
			</fo:block>
			
			<fo:block font-size="10pt" space-after="5mm">
			Conformément à la loi n°78-17 du 6 janvier 1978, le mandant autorise expressément le mandataire à saisir l’ensemble des informations contenues dans les présentes sur fichier informatique, le mandant pouvant exercer son libre accès et de rectification des données personnelles le concernant auprès du mandataire.
			</fo:block>
			
			<fo:block font-size="14pt" font-weight="bold"  space-after="5mm">
			14 – SUBSTITUTION :
			</fo:block>
			
			<fo:block font-size="10pt">
			Il est expressément convenu et accepté par le mandant que le mandataire pourra se substituer toute personne physique ou morale de son choix pour l’exécution du présent mandat, notamment en cas de disparition ou d’incapacité du mandataire ou lorsque l’exploitation de son fonds de commerce est confiée à une autre société ou mis en gérance.
			</fo:block>
			<fo:block font-size="10pt" space-after="5mm">
			Le mandat se poursuit aux conditions initiales sans qu’une quelconque dérogation aux dispositions contractuelles puisse être imposée au mandant.
			</fo:block>
			
			<fo:block font-size="14pt" font-weight="bold"  space-after="5mm">
			15 – AUTORISATION DE DOUBLE MANDATEMENT :
			</fo:block>
			
			<fo:block font-size="10pt" space-after="1cm">
			Par dérogation au premier alinéa de l’article 1161 du code civil, le mandant autorise le mandataire 
			à contracter un mandat avec chacun de ses locataires.
			</fo:block>
			
	
			

			
			<fo:block font-size="10pt" space-after="1cm">
			Version au 05/07/2017
			</fo:block>
			
			
			<fo:block font-size="10pt" font-weight="bold" >
			KELVALTO SASU au capital de 250 000 Euros dont le siège est à Wasquehal (59 447), 6 rue Konrad Adenauer - RCS Lille Métropole 790 608 715  Carte professionnelle en cours. Garant : Compagnie Européenne de Garanties et de Cautions - 15 Rue Hoche – Tour Kupka B – TSA 39999 – 92919 LA Défense Cedex Assurance RCP : GENERALI IARD – 7, Boulevard Haussman – 75009 PARIS - N° TVA intracommunautaire FR 05 790 608 715
			</fo:block>
			<fo:block font-size="10pt" font-weight="bold" space-after="30cm" >
			TRAITEMENT INFORMATIQUE : LOI DU 6 JANVIER 1978. Conformément à la loi n°78-17 du 6 janvier 1978, le mandant autorise expressément le mandataire à saisir l’ensemble des informations contenues dans les présentes sur fichier informatique, le mandant pouvant exercer son libre accès et de rectification des données personnelles le concernant auprès du mandataire.
			</fo:block>
			
			<fo:table table-layout="fixed" space-after="5mm" border="1px solid black">
				<fo:table-body>
					<fo:table-row font-size="10pt">
						<fo:table-cell display-align="center">
							<fo:block text-align="center">Annexe 1</fo:block>
						</fo:table-cell>
					</fo:table-row>
					<fo:table-row font-size="10pt">
						<fo:table-cell display-align="center">
							<fo:block text-align="center">Conditions d’éligibilité du ou des locataire(s) à la garantie de loyers impayés</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-body>
			</fo:table>
			
			<fo:block font-size="12pt" font-weight="bold" space-after="5mm">
			Afin de bénéficier de la garantie Loyers Impayés, KLEVALTO vérifiera la solvabilité du candidat locataire et s’assurera que les pièces fournis par le locataire soient valables. 
			</fo:block>
			
			<fo:block font-size="12pt" font-weight="bold" font-style="italic" space-after="5mm">
			Situation N° 1 : le mandant souscrit à l’offre KLEVALTO avec un locataire déjà en place 
			</fo:block>
			<fo:block font-size="10pt" space-after="3mm">
			La garantie de loyers impayés ne s’applique qu’après une période de 6 mois suivant la date de prise d’effet du mandat. Durant cette période de 6 mois, le locataire ne doit pas faire l’objet d’incidents de paiement (défaut de règlement d’une mensualité, règlement partiel de deux mensualités consécutives, retour impayé d’un règlement). 
			</fo:block>
			<fo:block font-size="10pt" space-after="3mm">
			Pièces à fournir :
			</fo:block>
			<fo:block font-size="10pt" space-after="3mm">
			- Bail, Klévalto vérifiera sa conformité, à savoir : 
			</fo:block>
			<fo:block font-size="10pt">
			•	présence d’une clause résolutoire de plein droit portant sur le non-respect des obligations du Locataire et notamment du non-paiement de ses Loyers charges et taxes comprises,
			</fo:block>
			<fo:block font-size="10pt">
			•	présence, en cas de pluralité de Locataires signataires d’un même bail une clause de solidarité et d’indivisibilité,
			</fo:block>
			<fo:block font-size="10pt" space-after="3mm">
			•	qu'il soit en conformité avec la loi en vigueur à la date de signature du bail, signé et paraphé par le ou les locataire(s)
			</fo:block>
			<fo:block font-size="10pt" space-after="5mm">
			- Pièce d’identité du ou des locataire(s) titulaire du bail
			</fo:block>
			
			<fo:block font-size="10pt" space-after="5mm">
			- Le cas échéant, acte de caution solidaire conforme à la réglementation en vigueur (vérifié par Klévalto)
			</fo:block>
			<fo:block font-size="10pt" space-after="1cm">
			- Le cas échéant, pièce d’identité du ou des garant(s)
			</fo:block>
			<fo:block font-size="12pt" font-weight="bold" font-style="italic"   space-after="5mm">
			Situation N° 2 : le mandant souscrit à l’offre KLEVALTO avec un logement vacant
			</fo:block>
			<fo:block font-size="10pt" space-after="5mm">
			La solvabilité du locataire doit être supérieure ou égal à 2,5 fois le montant du loyer charges comprises en traitement et salaire (voir formule de calcul ci-après). Toutes les sommes accessoires (APL, AAH, Pensions alimentaires, etc.) sont exclues du calcul de la solvabilité.
			</fo:block>
			<fo:block font-size="10pt" space-after="5mm">
			La liste des pièces à fournir pour constituer le dossier de solvabilité du candidat locataire est consultable dans le centre d’aide du site web www.klevalto.com.
			</fo:block>
			<fo:block font-size="10pt">
			Formule de calcul : 
			</fo:block>
			<fo:block font-size="10pt">
			Pour calculer une solvabilité, il suffit de diviser le revenu net mensuel de mon candidat locataire par le montant de mon loyer charges comprises.
			</fo:block>
			<fo:block font-size="10pt">
			Exemple : Je loue mon appartement 500 € charges comprises, mon candidat locataire perçoit 1250 € net mensuel, la solvabilité est donc de : 1250/500 = <fo:inline font-weight="bold">2.5</fo:inline>, il est éligible.
			</fo:block>

        </fo:flow>
      </fo:page-sequence>
     </fo:root>
</xsl:template>
</xsl:stylesheet>
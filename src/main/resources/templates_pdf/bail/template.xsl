<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
    xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
	<xsl:template match="sergictemplate">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="simpleA4" page-height="29.7cm" page-width="21cm" margin-top="2cm" margin-bottom="2cm" margin-left="2cm" margin-right="2cm">
					<fo:region-body/>
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="simpleA4">
				<fo:flow flow-name="xsl-region-body">

	<fo:block text-align="center" space-after="5mm">
        <fo:external-graphic src="url('src//main//resources//templates_pdf//bail//logo.jpg')" content-height="scale-to-fit" height="0.60in"  content-width="2.15in" scaling="non-uniform" ></fo:external-graphic>
    </fo:block>
					<fo:block font-size="14pt" font-weight="bold" text-align="center">
	BAIL A USAGE D’HABITATION
					</fo:block>

					<fo:block font-size="11pt" font-weight="bold" font-style="italic" space-after="5mm">
	Conditions particulières, indissociables des conditions générales signées et remises au(x) preneur(s)
					</fo:block>

					<fo:block font-size="11pt" font-weight="bold" space-after="5mm">
	1)<xsl:text>&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;</xsl:text>
						<fo:inline text-decoration="underline">DESIGNATION DES PARTIES</fo:inline> :
					</fo:block>

					<fo:block font-size="10pt" font-weight="bold" font-style="italic">
	Entre 	
					</fo:block>

					<fo:block font-size="10pt" font-weight="bold"  >
{PRENOM_BAILLEUR} {NOM_BAILLEUR}, 
					</fo:block>
					<fo:block font-size="10pt"   >
propriétaire des lieux loués, élisant domicile chez son mandataire SERGIC S.A.S. au capital de 22 041 688 euros, siège social situé 6 rue Konrad Adenauer Wasquehal (59290) – RCS Lille Métropole 428 748 909. {PRENOM_BAILLEUR} {NOM_BAILLEUR}, désigné au présent acte sous la dénomination « Le Bailleur ».
					</fo:block>

					<fo:block font-size="10pt" font-weight="bold" font-style="italic" >
et
					</fo:block>

					<fo:block font-size="10pt" font-weight="bold"   >
{PRENOM_LOCATAIRE} {NOM_LOCATAIRE}, demeurant {ADRESSE_LOCATAIRE}
					</fo:block>

					<fo:block font-size="10pt" space-after="5mm" >
désigné(s) ci-après sous la dénomination « Le(s) Preneur(s) ». 
					</fo:block>

					<fo:block font-size="11pt" font-weight="bold" space-after="5mm">
	2)<xsl:text>&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;</xsl:text>
						<fo:inline text-decoration="underline">DESIGNATION DES LOCAUX LOUES</fo:inline> :
					</fo:block>

					<fo:block font-size="10pt"   >
{TYPE_BIEN_1}, de {SURFACE_BIEN} M² situé au {ADRESSE_BIEN}.
					</fo:block>

					<fo:block font-size="10pt"  >
Le local comprend les éléments suivants, avec leurs équipements internes réservés à la jouissance exclusive du 
Preneur, tels que désignés ci-après :
					</fo:block>

					<fo:block font-size="10pt" space-after="5mm"  >
{EQUIPEMENTS_BIEN}
					</fo:block>

					<fo:block font-size="10pt"  space-after="5mm"  >
Le local comprend le droit d’usage des parties communes telles qu’elles existent actuellement, et sans qu’il soit nécessaire d’en faire une description plus détaillée, le(s) preneur(s) les ayant vues et visitées.
					</fo:block>


					<fo:block font-size="11pt" font-weight="bold" space-after="5mm">
	3)<xsl:text>&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;</xsl:text>
						<fo:inline text-decoration="underline">DUREE</fo:inline> :
					</fo:block>

					<fo:block font-size="10pt" space-after="5mm"  >
Le présent contrat prend effet le {DATE_EFFET_CONTRAT}, pour une durée de 3 ans.
					</fo:block>

					<fo:block font-size="11pt" font-weight="bold" space-after="5mm">
	4)<xsl:text>&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;</xsl:text>
						<fo:inline text-decoration="underline">LOYER ET PROVISIONS POUR CHARGES</fo:inline> :
					</fo:block>

					<fo:block font-size="10pt"    >
La présente location est consentie et acceptée pour le local ci-dessus, moyennant le loyer suivant : 
					</fo:block>
					<fo:block font-size="10pt">
{MONTANT_LOYER_EN_LETTRES} ({MONTANT_LOYER} euros).
					</fo:block>

					<fo:block font-size="10pt">
Les provisions pour charges s’élèvent à {MONTANT_CHARGES_EN_LETTRES} ({MONTANT_CHARGES} euros). 
Elles pourront être modifiées chaque année, en fonction des charges de l’immeuble.
					</fo:block>

					<fo:block font-size="10pt"    >
Le loyer et les provisions pour charges sont payables d’avance.
					</fo:block>
					<fo:block font-size="10pt"     space-after="5mm" >
Lorsque l’immeuble dans lequel se situe le bien loué est soumis au statut de la copropriété, le locataire est informé de l’existence d’un règlement de copropriété précisant la destination de l'immeuble, la jouissance et l'usage des parties privatives et communes et précisant la quote-part afférente au lot loué dans chacune des catégories de charges. Le preneur reconnait en avoir pris connaissance.
					</fo:block>

					<fo:block font-size="11pt" font-weight="bold" space-after="5mm">
	5)<xsl:text>&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;</xsl:text>
						<fo:inline text-decoration="underline">REVISION DU LOYER</fo:inline> :
					</fo:block>

					<fo:block font-size="10pt"    space-after="5mm">
Le loyer est révisable annuellement selon la variation positive de l’IRL (Indice de Référence des Loyers) sur l’année. 
					</fo:block>

					<fo:block font-size="11pt" font-weight="bold" space-after="5mm">
	6)<xsl:text>&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;</xsl:text>
						<fo:inline text-decoration="underline">DEPOT DE GARANTIE</fo:inline> :
					</fo:block>

					<fo:block font-size="10pt"      space-after="5mm">
	Le montant du dépôt de garantie est fixé à un mois de loyer hors charges, soit la somme de {MONTANT_LOYER_EN_LETTRES} ({MONTANT_LOYER} euros).
					</fo:block>

					<fo:block font-size="11pt" font-weight="bold" space-after="5mm">
	7)<xsl:text>&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;</xsl:text>
						<fo:inline text-decoration="underline">EQUIPEMENTS DE LA CUISINE</fo:inline> :
					</fo:block>


					<fo:block font-size="10pt"  space-after="5mm">
Par dérogation aux dispositions de l'article 3 "Etat des lieux, Entretien Courant et menues réparations" - Entretien du logement par le locataire, alinéa 7, la cuisine du logement étant équipée de : (détail des équipements)
					</fo:block>

					<fo:block font-size="10pt"  space-after="5mm">
Le preneur prendra à sa charge l'entretien, les menues réparations et plus généralement l'ensemble des réparations locatives telles que définies par la loi, sauf si elles ont été occasionnées par vétusté, malfaçon, vice de construction, cas fortuit ou force majeure. 
					</fo:block>

					<fo:block font-size="11pt" font-weight="bold" space-after="5mm">
	8)<xsl:text>&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;</xsl:text>
						<fo:inline text-decoration="underline">CONSTITUTION DU DOSSIER DU LOCATAIRE</fo:inline> :
					</fo:block>

					<fo:block font-size="10pt"  space-after="5mm">
	En cas de difficulté pour le locataire de fournir préalablement à la signature du bail certains documents et justificatifs sollicités par le bailleur ou son mandataire, le bail pourra néanmoins être signé à la condition expresse que le locataire s’engage à fournir les pièces demandées au plus tard le jour de l’entrée dans les lieux. La remise effective des clés reste conditionnée à la transmission par le locataire des pièces et justificatifs demandés par le bailleur et son mandataire.
					</fo:block>


					<fo:block font-size="11pt" font-weight="bold" space-after="5mm">
	9)<xsl:text>&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;</xsl:text>
						<fo:inline text-decoration="underline">FRAIS ET HONORAIRES</fo:inline> :
					</fo:block>


					<fo:block font-size="10pt" space-after="5mm">
En Article 5-I loi du 6 juillet 1989, alinéa 1, 2, 3 :
					</fo:block>

					<fo:block font-size="10pt" >
La rémunération des personnes mandatées pour se livrer ou prêter leur concours à l'entremise ou à la négociation d'une mise en location d'un logement, tel que défini aux articles 2 et 25-3, est à la charge exclusive du bailleur, à l'exception des honoraires liés aux prestations mentionnées aux deuxième et troisième alinéas du présent I.
					</fo:block>
					<fo:block font-size="10pt" >
Les honoraires des personnes mandatées pour effectuer la visite du preneur, constituer son dossier et rédiger un bail sont partagés entre le bailleur et le preneur. Le montant toutes taxes comprises imputé au preneur pour ces prestations ne peut excéder celui imputé au bailleur et demeure inférieur ou égal à un plafond par mètre carré de surface habitable de la chose louée fixé par voie réglementaire et révisable chaque année, dans des conditions définies par décret. Ces honoraires sont dus à la signature du bail.
					</fo:block>
					<fo:block font-size="10pt" space-after="5mm">
Les honoraires des personnes mandatées pour réaliser un état des lieux sont partagés entre le bailleur et le preneur. Le montant toutes taxes comprises imputé au locataire pour cette prestation ne peut excéder celui imputé au bailleur et demeure inférieur ou égal à un plafond par mètre carré de surface habitable de la chose louée fixé par voie réglementaire et révisable chaque année, dans des conditions définies par décret. Ces honoraires sont dus à compter de la réalisation de la prestation.
					</fo:block>

					<fo:block font-size="10pt"  space-after="5mm">
Plafonds relatifs à la visite, la constitution du dossier, la rédaction du bail : 12 €/m² en zone très tendues, 10 €/m² en zone tendue et 8 €/m² pour le reste du territoire 
					</fo:block>

					<fo:block font-size="10pt"  space-after="5mm">
Plafond relatif à l'état des lieux : 3 €/m² pour l'ensemble du territoire
					</fo:block>

					<fo:block font-size="11pt" font-weight="bold" space-after="5mm">
	10)<xsl:text>&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;&#xA0;</xsl:text>
						<fo:inline text-decoration="underline">INFORMATIONS COMPLEMENTAIRES</fo:inline> :
					</fo:block>

					<fo:block font-size="10pt"  text-decoration="underline" space-after="3mm">
Type d’habitat :<fo:inline font-weight="normal" text-decoration="none"> {TYPE_BIEN_2}</fo:inline>
					</fo:block>
					<fo:block font-size="10pt"  text-decoration="underline" space-after="3mm">
Régime juridique de l’immeuble :<fo:inline font-weight="normal" text-decoration="none"> {REGIME_JURIDIQUE_IMMEUBLE}</fo:inline>
					</fo:block>
					<fo:block font-size="10pt"  text-decoration="underline" space-after="3mm">
Période de construction :<fo:inline font-weight="normal" text-decoration="none"> {PERIODE_CONSTRUCTION}</fo:inline>
					</fo:block>
					<fo:block font-size="10pt"  text-decoration="underline" space-after="3mm">
Nombre de pièces habitables :<fo:inline font-weight="normal" text-decoration="none"> {NB_PIECES_HABITABLES}</fo:inline>
					</fo:block>
					<fo:block font-size="10pt"  text-decoration="underline" space-after="3mm">
Autres parties du logement et éléments d’équipements :<fo:inline font-weight="normal" text-decoration="none"> {AUTRES_PARTIES_LOGEMENT_ELEMENTS_EQ}</fo:inline>
					</fo:block>
					<fo:block font-size="10pt"  text-decoration="underline" space-after="3mm">
Modalité production chauffage :<fo:inline font-weight="normal" text-decoration="none"> {MOD_PROD_CHAUFFAGE}</fo:inline>
					</fo:block>
					<fo:block font-size="10pt"  text-decoration="underline" space-after="3mm">
Modalité production eau chaude sanitaire :<fo:inline font-weight="normal" text-decoration="none"> {MOD_PROD_EAU_CHAUDE_SANITAIRE}</fo:inline>
					</fo:block>
					<fo:block font-size="10pt"  text-decoration="underline" space-after="3mm">
Destination des locaux :<fo:inline font-weight="normal" text-decoration="none"> Usage d’habitation</fo:inline>
					</fo:block>
					<fo:block font-size="10pt"  text-decoration="underline" space-after="3mm">
Désignation des locaux et équipements accessoires de l’immeuble à usage privatif du locataire :<fo:inline font-weight="normal" text-decoration="none"> {ACCESSOIRES_USAGE_PRIVATIF_LOCATAIRE}</fo:inline>
					</fo:block>
					<fo:block font-size="10pt"  text-decoration="underline" space-after="3mm">
Enumération des locaux, parties, équipements et accessoires de l’immeuble à usage commun :<fo:inline font-weight="normal" text-decoration="none"> {ACCESSOIRES_USAGE_COMMUN}</fo:inline>
					</fo:block>
					<fo:block font-size="10pt"  text-decoration="underline" space-after="3mm">
Equipement d’accès aux technologies de l’information et de la communication :<fo:inline font-weight="normal" text-decoration="none"> {ACCESSOIRES_INFO_COMM}</fo:inline>
					</fo:block>
					<fo:block font-size="10pt"  text-decoration="underline" space-after="3mm">
Montant du loyer de référence :<fo:inline font-weight="normal" text-decoration="none"> {MONTANT_LOYER_REFERENCE} euros/m²</fo:inline>
					</fo:block>
					<fo:block font-size="10pt"  text-decoration="underline" space-after="3mm">
Montant du loyer majoré :<fo:inline font-weight="normal" text-decoration="none"> {MONTANT_LOYER_MAJORE} euros/m²</fo:inline>
					</fo:block>
					<fo:block font-size="10pt"  text-decoration="underline" space-after="3mm"  break-after="page">
Complément de Loyer :<fo:inline font-weight="normal" text-decoration="none"> {COMPLEMENT_LOYER} euros</fo:inline>
					</fo:block>

	<fo:block text-align="center"  break-after="page">
        <fo:external-graphic src="url('src//main//resources//templates_pdf//bail//bail_bottom_1.jpg')" width="100%" content-width="scale-down-to-fit" ></fo:external-graphic>
    </fo:block>
	<fo:block text-align="center"  break-after="page">
        <fo:external-graphic src="url('src//main//resources//templates_pdf//bail//bail_bottom_2.jpg')" width="100%" content-width="scale-down-to-fit" ></fo:external-graphic>
    </fo:block>
	<fo:block text-align="center"  break-after="page">
        <fo:external-graphic src="url('src//main//resources//templates_pdf//bail//bail_bottom_3.jpg')" width="100%" content-width="scale-down-to-fit" ></fo:external-graphic>
    </fo:block>
		<fo:block text-align="center"  break-after="page">
        <fo:external-graphic src="url('src//main//resources//templates_pdf//bail//bail_bottom_4.jpg')" width="100%" content-width="scale-down-to-fit" ></fo:external-graphic>
    </fo:block>
	
	</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
	
	<xsl:template match="employee">
		<fo:table-row>   
			<xsl:if test="designation = 'Manager'">
				<xsl:attribute name="font-weight">bold</xsl:attribute>
			</xsl:if>
			<fo:table-cell>
				<fo:block>
					<xsl:value-of select="id"/>
				</fo:block>
			</fo:table-cell>

			<fo:table-cell>
				<fo:block>
					<xsl:value-of select="name"/>
				</fo:block>
			</fo:table-cell>   
			<fo:table-cell>
				<fo:block>
					<xsl:value-of select="designation"/>
				</fo:block>
			</fo:table-cell>
		</fo:table-row>
	</xsl:template>
				
				
	
</xsl:stylesheet>


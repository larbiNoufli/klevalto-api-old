<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
    xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo" xmlns="http://www.w3.org/2000/svg">
	<xsl:template match="sergictemplate">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="simpleA4" page-height="29.7cm" page-width="21cm" margin-top="2cm" margin-bottom="2cm" margin-left="2cm" margin-right="2cm">
					<fo:region-body/>
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="simpleA4">
				<fo:flow flow-name="xsl-region-body">
			
				<fo:table table-layout="fixed" font-size="10pt" space-after="5mm" border="1px solid black">
					<fo:table-body>
						<fo:table-row font-size="10pt" height="4em">
							<fo:table-cell display-align="center" border="1px solid black">
								<fo:block text-align="center" font-size="10pt">
									{REFERENCE_RUM}
								</fo:block>
							</fo:table-cell>
							<fo:table-cell display-align="center" width="32em" border="1px solid black">
								<fo:block text-align="center" font-weight="bold" font-size="15pt">
									MANDAT DE PRELEVEMENT SEPA
								</fo:block>
							</fo:table-cell>
							<fo:table-cell display-align="center" border="1px solid black">
								<fo:block text-align="center" font-weight="bold">
									KLEVALTO
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<fo:table-row font-size="8pt">
							<fo:table-cell display-align="center" border="1px solid black" number-columns-spanned="3">
								<fo:block>
									En signant ce formulaire de mandat, vous autorisez KLEVALTO à envoyer des instructions à votre banque 
									pour débiter votre compte, et votre banque à débiter votre compte conformément aux instructions de KLEVALTO.
								</fo:block>
								<fo:block>
									Vous bénéficiez du droit d’être remboursé par votre banque selon les conditions décrites dans la convention que vous avez passée
									avec elle. Une demande de remboursement doit être présentée dans les 8 semaines suivant la date
									de débit de votre compte pour un prélèvement autorisé,
								</fo:block>
								<fo:block font-style="italic">Veuillez compléter les champs marqués *</fo:block>
							</fo:table-cell>
						</fo:table-row>
						<fo:table-row font-size="9pt">
							<fo:table-cell number-columns-spanned="3">
								<fo:table table-layout="fixed">
									<fo:table-body margin-left="0.5mm">
										<fo:table-row>
											<fo:table-cell width="120pt">
												<fo:block>
													Votre Nom
												</fo:block>
											</fo:table-cell>
											<fo:table-cell>
												<fo:block font-family="Courier">
													<fo:inline font-size="7pt">* </fo:inline>{NOM_BAILLEUR}
												</fo:block>
												<fo:block font-family="Arial" font-size="7pt">Nom / Prénoms du débiteur</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row number-rows-spanned="5">
											<fo:table-cell>
												<fo:block>
													Votre adresse
												</fo:block>
											</fo:table-cell>
											<fo:table-cell>
												<fo:block font-family="Courier">
													<fo:inline font-size="7pt">* </fo:inline>{ADRESSE_BIEN}
												</fo:block>
												<fo:block font-family="Courier">
													<fo:inline font-size="7pt">* </fo:inline><fo:inline font-family="Arial">Code Postal : </fo:inline>{CODE_POSTAL}
													<fo:inline font-size="7pt"> * </fo:inline>
													<fo:inline font-family="Arial">Ville : </fo:inline>{VILLE}
												</fo:block>
												<fo:block font-family="Courier">
													<fo:inline font-size="7pt">* </fo:inline><fo:inline font-family="Arial">Pays : </fo:inline>France..........................................
												</fo:block>
												<fo:block font-family="Courier">
													<fo:inline font-family="Arial">Téléphone : </fo:inline>{TELEPHONE} <fo:inline font-family="Arial">Email : </fo:inline>{EMAIL}
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell>
												<fo:block />
											</fo:table-cell>
											<fo:table-cell>
												<fo:block />
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell>
												<fo:block>
													Coordonnées de votre compte
												</fo:block>
											</fo:table-cell>
											<fo:table-cell>
												<fo:block>
													<fo:inline font-size="7pt">* </fo:inline>
													<fo:instream-foreign-object>
														<svg width="8" height="11">
														<g>
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(8,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(16,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(24,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(40,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(48,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(56,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(64,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(80,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(88,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(96,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(104,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(120,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(128,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(136,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(144,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(160,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(168,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(176,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(184,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(200,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(208,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(216,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(224,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(240,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(248,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(256,0)" />
															<text x="1" y="10" height="11"  letter-spacing="0.4mm" font-family="Verdana" font-size="12">{IBAN_0}</text>
															<text x="42" y="10" height="11" letter-spacing="0.4mm" font-family="Verdana" font-size="12">{IBAN_1}</text>
															<text x="82" y="10" height="11" letter-spacing="0.4mm" font-family="Verdana" font-size="12">{IBAN_2}</text>
															<text x="122" y="10" height="11" letter-spacing="0.4mm" font-family="Verdana" font-size="12">{IBAN_3}</text>
															<text x="162" y="10" height="11" letter-spacing="0.4mm" font-family="Verdana" font-size="12">{IBAN_4}</text>
															<text x="202" y="10" height="11" letter-spacing="0.4mm" font-family="Verdana" font-size="12">{IBAN_5}</text>
															<text x="242" y="10" height="11" letter-spacing="0.4mm" font-family="Verdana" font-size="12">{IBAN_6}</text>
															</g>
														</svg>
													</fo:instream-foreign-object>
												</fo:block>
												<fo:block font-size="7pt">
													Numéro d’identification international du compte bancaire – IBAN (International Bank Account Number)
												</fo:block>
												<fo:block>
													<fo:inline font-size="7pt">* </fo:inline>{BIC}
												</fo:block>
												<fo:block font-size="7pt">
													Code international d’identification de votre banque - BIC (Bank Identifier Code)
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell>
												<fo:block>
													Nom du Créancier
												</fo:block>
											</fo:table-cell>
											<fo:table-cell>
												<fo:block font-family="Courier" font-weight="bold">
													KLEVALTO
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell>
												<fo:block>
													ICS
												</fo:block>
											</fo:table-cell>
											<fo:table-cell>
												<fo:block font-family="Arial">
													FR04ZZZ635094
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell>
												<fo:block>
													Adresse créancier
												</fo:block>
											</fo:table-cell>
											<fo:table-cell font-family="Courier">
												<fo:block font-weight="bold">
													KLEVALTO – 6 RUE KONRAD ADENAUER
												</fo:block>
												<fo:block font-weight="bold">
													59000 WASQUEHAL
												</fo:block>
												<fo:block font-weight="bold">
													FRANCE
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell>
												<fo:block>
													Type de paiement
												</fo:block>
											</fo:table-cell>
											<fo:table-cell font-family="Courier">
												<fo:block>
													<fo:inline font-size="7pt">* </fo:inline>
													Paiement récurrent / répétitif
													<fo:instream-foreign-object>
														<svg width="8" height="8">
															<rect width="8" height="8" style="stroke-width:0.5;stroke:rgb(0,0,0)" />
														</svg>
													</fo:instream-foreign-object>
													Paiement ponctuel
													<fo:instream-foreign-object>
														<svg width="8" height="8">
															<rect width="8" height="8" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" />
														</svg>
													</fo:instream-foreign-object>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell>
												<fo:block>
													Signé à
												</fo:block>
											</fo:table-cell>
											<fo:table-cell font-family="Courier">
												<fo:block>
													<fo:inline font-size="7pt">* </fo:inline>........................      <fo:instream-foreign-object>
														<svg width="8" height="11">
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(8,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(20,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(28,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(40,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(48,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(56,0)" />
															<rect width="8" height="11" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" transform="translate(64,0)" />
														</svg>
													</fo:instream-foreign-object>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell>
												<fo:block />
											</fo:table-cell>
											<fo:table-cell font-family="Arial" font-size="8pt">
												<fo:block>
													Lieu                                                                                      Date
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell>
												<fo:block>
													Signature(s)
												</fo:block>
											</fo:table-cell>
											<fo:table-cell font-family="Courier">
												<fo:block>
													Veuillez signer ici:
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell>
												<fo:block />
											</fo:table-cell>
											<fo:table-cell font-family="Courier">
												<fo:block>
													<fo:instream-foreign-object>
														<svg width="340" height="32">
															<rect width="340" height="32" style="stroke-width:0.5;stroke:rgb(0,0,0);fill:white" />
														</svg>
													</fo:instream-foreign-object>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
										<fo:table-row>
											<fo:table-cell number-columns-spanned="3">
												<fo:block font-size="7pt">
													Note : Vos droits concernant le présent mandat sont expliqués dans un document que vous pouvez obtenir auprès de votre banque
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</fo:table-body>
								</fo:table>
							</fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>

	</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
	
	
				
				
	
</xsl:stylesheet>


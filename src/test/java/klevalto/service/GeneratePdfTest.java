package klevalto.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import klevalto.SergicApp;
import klevalto.service.dto.InfosBailPdfDTO;
import klevalto.service.dto.InfosMandatPdf;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SergicApp.class)
@ActiveProfiles(profiles = "test")
public class GeneratePdfTest {
	
    public static final String PATH_MANDAT_TEMPLATE;
    public static final String PATH_MANDAT_DATA;
    
    public static final String PATH_BAIL_TEMPLATE;
    public static final String PATH_BAIL_DATA;
    
    public static final String RESOURCES_DIR_SEPA;
    public static final String PATH_OUTPUT_MANDAT;
    public static final String PATH_OUTPUT_BAIL;


    static {
    	
        PATH_MANDAT_TEMPLATE = "src//test//resources//templates_pdf//mandat//template.xsl";
        PATH_MANDAT_DATA = "src//test//resources//templates_pdf//mandat//data.xml";
        
        PATH_BAIL_TEMPLATE = "src//test//resources//templates_pdf//bail//template.xsl";
        PATH_BAIL_DATA = "src//test//resources//templates_pdf//bail//data.xml";
        
        RESOURCES_DIR_SEPA = "src//test//resources//templates_pdf//mandat_prelevement//";
        
        PATH_OUTPUT_MANDAT = "target//test-results//mandatTest.pdf";
        PATH_OUTPUT_BAIL = "target//test-results//bailTest.pdf";
    }
	
    @Autowired
	private GeneratePdf generatePdf;
	
	
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    
    @Test
    public void generateBail() throws Exception {
    	
    	// GIVEN
    	
		InfosBailPdfDTO bailPdfDTO = InfosBailPdfDTO.builder()
				.nomBailleur("Noufli")
				.prenomBailleur("Larbi")
				.civiliteBailleur("M")	
				.nomLocataire("Berthe")
				.prenomLocataire("nom locataire")
				.adresseLocataire("34 rue jules")
				.typeBien1("TypeBien")
				.surfaceBien("surface")
				.equipementsBien("equipeBien")
				.adresseBien("adresse du bien ")
				.dateEffetDuContrat("23/09/2017")
				.montantLoyer("600")
				.montantChargesEnLettres("Six")
				.montantCharges("150")
				.montantLoyerEnLettres("Cent cinquante")
				.typeBien2("typeBien2")
				.regimeJuridiqueImmeuble("regimeImeuble")
				.periodeConstruction("Avant 1959")
				.nbPiecesHabitables("5")
				.autresPartiesLogementElementsEq("AutreParties")
				.modeProdChauffage("producEauChaude")
				.modeProdEauChaudeSanitaire("EauSanitaire")
				.accessoiresUsagePrivatifLocataire("accesoiresUsageProvatifLocataire")
				.accessoiresUsageCommun("AccesoiresUsageCommun")
				.accesoiresInfoCommun("accesoiresInfoCommun")
				.montantLoyerRef("LoyerRef")
				.complementLoyer("complementLoyer")
				.montantLoyerMajore("LoyerMajore")
				.build();
    	
    	// THEN
    	
    	generatePdf.generateBail(bailPdfDTO, PATH_BAIL_TEMPLATE, PATH_BAIL_DATA, PATH_OUTPUT_BAIL);

    	
    }
    
    
    @Test
    public void generateMandat() throws Exception {
    	
    	//GIVEN
    
    	InfosMandatPdf mandatPdfDTO = InfosMandatPdf.builder()
    			.nomBailleur("Noufli")
    			
    			.nomBailleur("Noufli")
    	    	.prenomBailleur("Larbi")
    	    	.civiliteBailleur("M")
    	    	.adresseBailleur("adresse bailleur")
    	    	.telephoneBailleur("0303030303")
    	    	.telephoneMobileBailleur("0606060606")
    	    	.mailBailleur("mailBailleur@bailleur.fr")
    	    	.nationaliteBailleur("nationaliteBailleur")
    	    	.situationFamilialeBailleur("MARIE")
    	    	.nomBailleur("nomBailleur")
    	    	.adresseImmeuble("adresseImmeuble")
    	    	.periodeConstructionImmeuble("Avant 1930")
    	    	.natureLocal("natureLocal")
    	    	.surfaceHabitable("surfaceHabitable")
    	    	.surfaceAnnexes("surfaceAnnexes")
    	    	.regimeFiscal("regimeFical")
    	    	.numeroCopropriete("numCopro")
    	    	.nomImmeuble("NomImmeuble")
    	    	.adresseImmeuble("adresseImmeuble")
    			.build();
    	
    	// THEN
    	
    	generatePdf.generateMandatGestion(mandatPdfDTO, PATH_MANDAT_TEMPLATE,PATH_MANDAT_DATA ,PATH_OUTPUT_MANDAT);
    	
    	
    }

}

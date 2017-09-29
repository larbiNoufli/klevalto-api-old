package klevalto.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.Scanner;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.stereotype.Service;

import klevalto.constante.ConstantsMsecureDocument;
import klevalto.service.dto.InfosBailPdfDTO;
import klevalto.service.dto.InfosCommunesPdf;
import klevalto.service.dto.InfosMandatPdf;
import lombok.extern.slf4j.Slf4j;
/**
 * 
 * Service to generate pdf bail or mandat
 *
 */
@Service
@Slf4j
public class GeneratePdf {
	
    
    public static  void convertToPDF(String content, StreamSource xmlSource, String pathOutPut) throws IOException, FOPException, TransformerException {
    	
        // create an instance of fop factory
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        // a user agent is needed for transformation
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        // Setup output

        try ( OutputStream out = new java.io.FileOutputStream(pathOutPut)) {
            // Construct fop with desired output format
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            // Setup XSLT
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(new StringReader(content)));
            

            // Resulting SAX events (the generated FO) must be piped through to
            // FOP
            Result res = new SAXResult(fop.getDefaultHandler());

            // Start XSLT transformation and FOP processing
            // That's where the XML is first transformed to XSL-FO and then
            // PDF is created
            transformer.transform(xmlSource, res);
            
        }
    }
    
    /**
     * Permet de generer un bail avec les informations passées en paramétre
     * @param bailPdfDTO
     * @param pathTemplate
     * @param pathData
     * @param pathOutput
     * @throws FOPException
     * @throws IOException
     * @throws TransformerException
     */
    public  void generateBail(InfosBailPdfDTO bailPdfDTO, String pathTemplate, String pathData, String pathOutput) throws FOPException, IOException, TransformerException {
    	
    	
    	String xsltFileToTransform = extracted(pathTemplate).useDelimiter("\\Z").next();
    	StreamSource xmlSource = new StreamSource(new File(pathData));
    	
    
    	// Le Bailleur
    	
        xsltFileToTransform = replaceInfosBailleur(bailPdfDTO, xsltFileToTransform);

		// Le Locataire
        
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.NOM_LOCATAIRE, ( bailPdfDTO.getNomLocataire())== null ? "" : bailPdfDTO.getNomLocataire());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.PRENOM_LOCATAIRE, ( bailPdfDTO.getPrenomLocataire())== null ? "" : bailPdfDTO.getPrenomLocataire());
        
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.ADRESSE_LOCATAIRE, ( bailPdfDTO.getAdresseLocataire())== null ? "" : bailPdfDTO.getAdresseLocataire());
        
        // Le Bien
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.TYPE_BIEN_1, ( bailPdfDTO.getTypeBien1())== null ? "" : bailPdfDTO.getTypeBien1());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.SURFACE_BIEN, ( bailPdfDTO.getSurfaceBien())== null ? "" : bailPdfDTO.getSurfaceBien());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.EQUIPEMENTS_BIEN, ( bailPdfDTO.getEquipementsBien())== null ? "" : bailPdfDTO.getEquipementsBien());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.ADRESSE_BIEN, ( bailPdfDTO.getAdresseBien())== null ? "" : bailPdfDTO.getAdresseBien());
        
        
        // Duree
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.DATE_EFFET_CONTRAT, ( bailPdfDTO.getDateEffetDuContrat())== null ? "" : bailPdfDTO.getDateEffetDuContrat());
        
        // Loyer et charges
        
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.MONTANT_LOYER, ( bailPdfDTO.getMontantCharges())== null ? "" : bailPdfDTO.getMontantCharges());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.MONTANT_LOYER_EN_LETTRES, ( bailPdfDTO.getMontantLoyerEnLettres())== null ? "" : bailPdfDTO.getMontantLoyerEnLettres());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.MONTANT_CHARGES, ( bailPdfDTO.getMontantCharges())== null ? "" : bailPdfDTO.getMontantCharges());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.MONTANT_CHARGES_EN_LETTRES, ( bailPdfDTO.getMontantChargesEnLettres())== null ? "" : bailPdfDTO.getMontantChargesEnLettres());
        
        
        // Infos complementaire
        
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.TYPE_BIEN_2, ( bailPdfDTO.getTypeBien2())== null ? "" : bailPdfDTO.getTypeBien2());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.REGIME_JURIDIQUE_IMMEUBLE, ( bailPdfDTO.getRegimeJuridiqueImmeuble())== null ? "" : bailPdfDTO.getRegimeJuridiqueImmeuble());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.PERIODE_CONSTRUCTION, ( bailPdfDTO.getPeriodeConstruction())== null ? "" : bailPdfDTO.getPeriodeConstruction());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.NB_PIECES_HABITABLES, ( bailPdfDTO.getNbPiecesHabitables())== null ? "" : bailPdfDTO.getNbPiecesHabitables());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.AUTRES_PARTIES_LOGEMENT_ELEMENTS_EQ, ( bailPdfDTO.getAutresPartiesLogementElementsEq())== null ? "" : bailPdfDTO.getAutresPartiesLogementElementsEq());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.MOD_PROD_CHAUFFAGE, ( bailPdfDTO.getModeProdChauffage())== null ? "" : bailPdfDTO.getModeProdChauffage());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.MOD_PROD_EAU_CHAUDE_SANITAIRE, ( bailPdfDTO.getModeProdEauChaudeSanitaire())== null ? "" : bailPdfDTO.getModeProdEauChaudeSanitaire());
        
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.ACCESSOIRES_USAGE_PRIVATIF_LOCATAIRE, ( bailPdfDTO.getAccessoiresUsagePrivatifLocataire())== null ? "" : bailPdfDTO.getAccessoiresUsagePrivatifLocataire());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.ACCESSOIRES_USAGE_COMMUN, ( bailPdfDTO.getAccessoiresUsageCommun())== null ? "" : bailPdfDTO.getAccessoiresUsageCommun());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.ACCESSOIRES_INFO_COMM, ( bailPdfDTO.getAccesoiresInfoCommun())== null ? "" : bailPdfDTO.getAccesoiresInfoCommun());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.MONTANT_LOYER_REFERENCE, ( bailPdfDTO.getMontantLoyerRef())== null ? "" : bailPdfDTO.getMontantLoyerRef());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.MONTANT_LOYER_MAJORE, ( bailPdfDTO.getMontantLoyerMajore())== null ? "" : bailPdfDTO.getMontantLoyerMajore());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.COMPLEMENT_LOYER, ( bailPdfDTO.getComplementLoyer())== null ? "" : bailPdfDTO.getComplementLoyer());
		
        
        log.info("XSL generé pour le bailleur [{}] \n {}", bailPdfDTO.getNomBailleur(), xsltFileToTransform);
        
        convertToPDF(xsltFileToTransform, xmlSource, pathOutput);
		
		
	}

    /**
     * Permet de remplacer les informations bailleur du strinf en paramétre
     * @param infosCommunesPDF
     * @param xsltFileToTransform
     * @return
     */
	private static String replaceInfosBailleur(InfosCommunesPdf infosCommunesPDF, String xsltFileToTransform) {
		
		// civ
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.CIVILITE_BAILLEUR, ( infosCommunesPDF.getCiviliteBailleur()));
        // nom
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.NOM_BAILLEUR,infosCommunesPDF.getNomBailleur());
        // prenom
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.PRENOM_BAILLEUR, infosCommunesPDF.getPrenomBailleur());
		return xsltFileToTransform;
	}
    
	/**
	 * Permet de generer un PDF de type mandat avec les paramétres en entrées
	 * @param mandatPdfDTO
	 * @throws FOPException
	 * @throws IOException
	 * @throws TransformerException
	 */
    public void generateMandatGestion(InfosMandatPdf mandatPdfDTO, String pathTemplate, String pathData, String pathOutput) throws FOPException, IOException, TransformerException {
    	
    	String xsltFileToTransform = extracted(pathTemplate).useDelimiter("\\Z").next();
    	StreamSource xmlSource = new StreamSource(new File(pathData));
    	
    	// Le Bailleur
    	
        xsltFileToTransform = replaceInfosBailleur(mandatPdfDTO, xsltFileToTransform);
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.ADRESSE_BAILLEUR, ( mandatPdfDTO.getAdresseBailleur())== null ? "" : mandatPdfDTO.getAdresseBailleur());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.TELEPHONE, ( mandatPdfDTO.getTelephoneBailleur())== null ? "" : mandatPdfDTO.getTelephoneBailleur());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.TELEPHONE_PORTABLE, ( mandatPdfDTO.getTelephoneMobileBailleur())== null ? "" : mandatPdfDTO.getTelephoneMobileBailleur());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.EMAIL, ( mandatPdfDTO.getMailBailleur())== null ? "" : mandatPdfDTO.getMailBailleur());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.NATIONALITE, ( mandatPdfDTO.getNationaliteBailleur())== null ? "" : mandatPdfDTO.getNationaliteBailleur());
        
        
        // Les Biens
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.NOM_IMMEUBLE, ( mandatPdfDTO.getNomImmeuble())== null ? "" : mandatPdfDTO.getNomImmeuble());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.ADRESSE_IMMEUBLE, ( mandatPdfDTO.getAdresseImmeuble())== null ? "" : mandatPdfDTO.getAdresseImmeuble());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.PERIODE_CONSTRUCTION, ( mandatPdfDTO.getPeriodeConstructionImmeuble())== null ? "" : mandatPdfDTO.getPeriodeConstructionImmeuble());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.NATURE_LOCAL, ( mandatPdfDTO.getNatureLocal())== null ? "" : mandatPdfDTO.getNatureLocal());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.SURFACE_HABITABLE, ( mandatPdfDTO.getSurfaceHabitable())== null ? "" : mandatPdfDTO.getSurfaceHabitable());
        
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.SURFACE_ANNEXES, ( mandatPdfDTO.getSurfaceAnnexes())== null ? "" : mandatPdfDTO.getSurfaceAnnexes());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.REGIME_FISCAL, ( mandatPdfDTO.getRegimeFiscal())== null ? "" : mandatPdfDTO.getRegimeFiscal());
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.NUMERO_COPROPRIETE, ( mandatPdfDTO.getNumeroCopropriete())== null ? "" : mandatPdfDTO.getNumeroCopropriete());
        
        xsltFileToTransform = xsltFileToTransform.replaceAll(ConstantsMsecureDocument.MANDAT_ID, ( mandatPdfDTO.getMandatId())== null ? "" : mandatPdfDTO.getMandatId());
        
        log.info("XSL Mandat generé pour le bailleur [{}] \n {}", mandatPdfDTO.getNomBailleur(), xsltFileToTransform);
        
    	
    	convertToPDF(xsltFileToTransform, xmlSource, pathOutput);
    	
    }
    

    
	private static Scanner extracted(String in) throws FileNotFoundException {
		return new Scanner(new File(in));
	}
   

}

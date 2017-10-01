
package klevalto.service;

import java.io.IOException;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.XmlMappingException;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import klevalto.exeptions.YousignException_Exception;
import klevalto.yousign.util.Authentication;
import klevalto.yousign.util.SecurityHeader;
import yousign.cosignWS.wsdl.GetInfosFromCosignatureDemand;
import yousign.cosignWS.wsdl.GetInfosFromCosignatureDemandResponse;
import yousign.cosignWS.wsdl.ObjectFactory;

public class YousignCosignWsClientService extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(YousignCosignWsClientService.class);
	
	@Value("${yousign.username}")
	private String userName;
	
	@Value("${yousign.password}")
	private String password;
	
	@Value("${yousign.apikey}")
	private String apikey;
	
	@Value("${yousign.urlYousignCosignWS}")
	private String urlYousignCosignWS;

	/**
	 * Permet d'avoir toutes les informations sur un dossier Yousign avec l'idDemand et le token 
	 * @param idDemand identifiant de la demande Yousign
	 * @param token token de la demande de signature Yousign
	 * @return GetInfosFromCosignatureDemandResponse contenant toutes les informations sur le dossier Yousign
	 * @throws XmlMappingException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public GetInfosFromCosignatureDemandResponse getInfosFromCosignatureDemand(int idDemand, String token) throws YousignException_Exception  {

		ObjectFactory factory = new ObjectFactory();
		
		GetInfosFromCosignatureDemand value = new GetInfosFromCosignatureDemand();
		value.setIdDemand(idDemand);
		value.setToken(token);
		
		JAXBElement<GetInfosFromCosignatureDemand> request = factory.createGetInfosFromCosignatureDemand(value);
		
		
		JAXBElement<GetInfosFromCosignatureDemandResponse> response;
		
		response = (JAXBElement<GetInfosFromCosignatureDemandResponse>) getWebServiceTemplate()
				.marshalSendAndReceive(urlYousignCosignWS,
						request,
						new SecurityHeader(
		                        new Authentication(userName, password, apikey)));
		
		return response.getValue();
		
	}

}

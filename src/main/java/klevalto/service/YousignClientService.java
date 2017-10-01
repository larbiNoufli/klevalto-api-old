
package klevalto.service;

import java.io.IOException;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.XmlMappingException;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import klevalto.yousign.util.Authentication;
import klevalto.yousign.util.SecurityHeader;
import yousign.authenticationWS.wsdl.Connect;
import yousign.authenticationWS.wsdl.ConnectResponse;
import yousign.authenticationWS.wsdl.ObjectFactory;

public class YousignClientService extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(YousignClientService.class);
	
	@Value("${yousign.username}")
	private String userName;
	
	@Value("${yousign.password}")
	private String password;
	
	@Value("${yousign.apikey}")
	private String apikey;
	
	@Value("${yousign.urlYousignAuthenticationWS}")
	private String urlYousignAuthenticationWS;

	public ConnectResponse getConnection() throws XmlMappingException, IOException {

		ObjectFactory factory = new ObjectFactory();
		
		JAXBElement<Connect> request = factory.createConnect(new Connect());
		
		
		@SuppressWarnings("unchecked")
		JAXBElement<ConnectResponse> response = (JAXBElement<ConnectResponse>) getWebServiceTemplate()
				.marshalSendAndReceive(urlYousignAuthenticationWS,
						request,
						new SecurityHeader(
                                new Authentication(userName, password, apikey)));

		
		return response.getValue();
		
	}

}

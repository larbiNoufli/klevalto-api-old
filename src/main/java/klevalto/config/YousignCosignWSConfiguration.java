
package klevalto.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import klevalto.service.YousignCosignWsClientService;

@Configuration
public class YousignCosignWSConfiguration {
	
	@Value("${yousign.urlYousignCosignWS}")
	private String urlYousignCosignWS;

	@Bean
	public Jaxb2Marshaller marshallerCosign() {
		Jaxb2Marshaller marshallerCosign = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshallerCosign.setContextPath("yousign.cosignWS.wsdl");
		return marshallerCosign;
	}

	@Bean
	public YousignCosignWsClientService yousignCosignWsClientService(Jaxb2Marshaller marshallerCosign) {
		
		YousignCosignWsClientService client = new YousignCosignWsClientService();
		client.setDefaultUri(urlYousignCosignWS);
		client.setMarshaller(marshallerCosign);
		client.setUnmarshaller(marshallerCosign);
		return client;
	}

}

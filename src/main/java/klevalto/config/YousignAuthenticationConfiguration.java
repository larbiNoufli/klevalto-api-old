
package klevalto.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import klevalto.service.YousignClientService;

@Configuration
public class YousignAuthenticationConfiguration {
	
	@Value("${yousign.urlYousignAuthenticationWS}")
	private String urlYousignAuthenticationWS;

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("yousign.authenticationWS.wsdl");
		return marshaller;
	}

	@Bean
	public YousignClientService yousignClient(Jaxb2Marshaller marshaller) {
		
		YousignClientService client = new YousignClientService();
		client.setDefaultUri(urlYousignAuthenticationWS);
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}

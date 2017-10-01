package klevalto.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * Properties specific to JHipster.
 * <p>
 * Properties are configured in the application.yml file.
 */
@Data
@ConfigurationProperties(prefix = "yousign", ignoreUnknownFields = false)
public final class YousignProperties {
	
	
    private String username;
    private String password;
    private String apikey;
    
    

}

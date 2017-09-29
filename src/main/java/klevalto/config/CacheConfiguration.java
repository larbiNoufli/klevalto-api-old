package klevalto.config;

import java.util.concurrent.TimeUnit;

import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.jhipster.config.JHipsterProperties;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache("users", jcacheConfiguration);
            cm.createCache("tiers", jcacheConfiguration);
            cm.createCache(klevalto.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(klevalto.domain.Tiers.class.getName(), jcacheConfiguration);
            cm.createCache(klevalto.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(klevalto.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(klevalto.domain.Tiers.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(klevalto.domain.AdressePostale.class.getName(), jcacheConfiguration);
            cm.createCache(klevalto.domain.Pole.class.getName(), jcacheConfiguration);
            cm.createCache(klevalto.domain.Profil.class.getName(), jcacheConfiguration);
            cm.createCache(klevalto.domain.Role.class.getName(), jcacheConfiguration);
            cm.createCache(klevalto.domain.Mandat.class.getName(), jcacheConfiguration);
            cm.createCache(klevalto.domain.Bail.class.getName(), jcacheConfiguration);
            cm.createCache(klevalto.domain.Bien.class.getName(), jcacheConfiguration);
            cm.createCache(klevalto.domain.TypeBien.class.getName(), jcacheConfiguration);
            cm.createCache(klevalto.domain.LocataireBien.class.getName(), jcacheConfiguration);
            cm.createCache(klevalto.domain.AssocMandatBien.class.getName(), jcacheConfiguration);
            cm.createCache(klevalto.domain.AssocBailBien.class.getName(), jcacheConfiguration);
            cm.createCache(klevalto.domain.Prelevement.class.getName(), jcacheConfiguration);
            cm.createCache(klevalto.domain.UtilisationRibTiers.class.getName(), jcacheConfiguration);
            cm.createCache(klevalto.domain.RibTiers.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}

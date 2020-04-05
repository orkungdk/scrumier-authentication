/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.persistance.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;

import tr.com.ogedik.commons.cache.CommonCacheConfig;

/**
 * @author orkun.gedik
 */
@Configuration
public class AuthenticationCacheConfig {

  @Value("${cache.instanceName}")
  private String instanceName;

  @Value("${cache.configuration.name}")
  private String configName;

  @Bean
  public Config configure() {
    return new Config().setInstanceName(instanceName)
        .addMapConfig(CommonCacheConfig.initMapConfig().setName(configName));
  }
}

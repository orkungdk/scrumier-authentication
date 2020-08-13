package tr.com.ogedik.authentication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;

/**
 * @author orkun.gedik
 */
@Configuration
public class AuthenticationConfig {

  @Value("${cache.instanceName}")
  private String instanceName;

  @Value("${cache.configuration.name}")
  private String configName;

  /**
   * Returns an instance of {@link Config}
   *
   * @return {@link Config}
   */
  @Bean
  public Config configure() {
    return new Config().setInstanceName(this.instanceName).addMapConfig(initMapConfig().setName(this.configName));
  }

  /**
   * Returns an instance of {@link MapConfig}.
   *
   * @implNote MapConfig name should be set after this method has been called via {@link MapConfig#setName}
   *  E.g -> new Config().setInstanceName(instanceName).addMapConfig(initMapConfig().setName(configName))
   *
   * @return {@link MapConfig}
   */
  private MapConfig initMapConfig() {
    return new MapConfig().setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
        .setEvictionPolicy(EvictionPolicy.LRU)
        .setTimeToLiveSeconds(2000);
  }
}

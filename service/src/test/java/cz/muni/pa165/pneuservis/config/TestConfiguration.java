package cz.muni.pa165.pneuservis.config;

import cz.muni.pa165.pneuservis.persistence.config.PersistenceConfiguration;
import cz.muni.pa165.pneuservis.service.AdditionalServiceService;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by peter on 11/18/16.
 */

@Configuration
@ComponentScan(basePackageClasses = {AdditionalServiceService.class})
@Import(PersistenceConfiguration.class)
public class TestConfiguration {
    @Bean
    public Mapper dozer() {
        return new DozerBeanMapper();
    }
}

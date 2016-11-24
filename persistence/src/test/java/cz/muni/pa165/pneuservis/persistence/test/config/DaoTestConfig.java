package cz.muni.pa165.pneuservis.persistence.test.config;

import cz.muni.pa165.pneuservis.persistence.repository.UserRepository;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Michal Krajcovic <mkrajcovic@mail.muni.cz>
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackageClasses = UserRepository.class)
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@PropertySource("classpath:hibernate-test.properties")
public class DaoTestConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        factory.setJpaPropertyMap(jpaProperties());
        return factory;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2).build();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    private Map<String, Object> jpaProperties()
    {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.connection.url", env.getProperty("hibernate.connection.url"));
        properties.put("hibernate.connection.driver_class", env.getProperty("hibernate.connection.driver_class"));
        properties.put("hibernate.cache.provider_class", env.getProperty("hibernate.cache.provider_class"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql", Boolean.class));
        properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql", Boolean.class));
        return properties;
    }
}

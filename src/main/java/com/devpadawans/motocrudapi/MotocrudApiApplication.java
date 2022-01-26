package com.devpadawans.motocrudapi;

import lombok.val;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication(scanBasePackages = {"com.devpadawans.motocrudapi"} , exclude = { DataSourceAutoConfiguration.class })
@EnableJpaRepositories("com.devpadawans.motocrudapi")
@EntityScan("com.devpadawans.motocrudapi")
public class MotocrudApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MotocrudApiApplication.class, args);
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        val sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        val registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactoryBean(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        val factory = new LocalContainerEntityManagerFactoryBean();
        val jpaProperties = new Properties();

        factory.setDataSource(dataSource);
        factory.setJpaVendorAdapter(jpaVendorAdapter);
        factory.setValidationMode(ValidationMode.NONE);
        factory.setSharedCacheMode(SharedCacheMode.ENABLE_SELECTIVE);
        factory.setPackagesToScan("com.devpadawans.motocrudapi.model");

        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        jpaProperties.put("hibernate.hbm2ddl.auto", "create");
        jpaProperties.put("hibernate.show_sql", true);
        jpaProperties.put("hibernate.discriminator.ignore_explicit_for_joined", true);

        jpaProperties.put("spring.jpa.properties.org.hibernate.envers.store_data_at_delete", "true");

        jpaProperties.put("org.hibernate.envers.allow_identifier_reuse", "true");

        factory.setJpaProperties(jpaProperties);
        factory.afterPropertiesSet();

        return factory.getObject();
    }

}

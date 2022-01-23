package com.devpadawans.motocrudapi.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.sql.DataSource;
import java.util.Properties;

@Slf4j
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
@ComponentScan(
        basePackages = {
                "com.devpadawans.motocrudapi.model",
                "com.devpadawans.motocrudapi.repository",
                "com.devpadawans.motocrudapi.service"
        },
        basePackageClasses = {PostgresProperty.class}
)
@EnableTransactionManagement
@EnableJpaRepositories("com.devpadawans.motocrudapi.repository")
@PropertySource({"classpath:application-localdev"})
public class PostgresJDBCConfig {

    private final PostgresProperty properties;

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        val adapter = new HibernateJpaVendorAdapter();

        adapter.setDatabase(Database.POSTGRESQL);
        adapter.setGenerateDdl(false);

        return adapter;
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
        jpaProperties.put("hibernate.hbm2ddl.auto", properties.getHibernate().getDdlAuto());
        jpaProperties.put("hibernate.show_sql", properties.getHibernate().getShowSql());
        jpaProperties.put("hibernate.format_sql", properties.getHibernate().getFormatSql());
        jpaProperties.put("hibernate.ejb.naming_strategy", properties.getHibernate().getEjb().getNamingStrategy());
        jpaProperties.put("hibernate.discriminator.ignore_explicit_for_joined", true);
        jpaProperties.put("hibernate.temp.use_jdbc_metadata_defaults", properties.getHibernate().getUseJdbcMetadataDefaults());
        jpaProperties.put("hibernate.jdbc.lob.non_contextual_creation", properties.getHibernate().getNonContextualCreation());

        jpaProperties.put("spring.jpa.properties.org.hibernate.envers.store_data_at_delete", "true");

        jpaProperties.put("org.hibernate.envers.allow_identifier_reuse", "true");

        factory.setJpaProperties(jpaProperties);
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        val sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        val registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}



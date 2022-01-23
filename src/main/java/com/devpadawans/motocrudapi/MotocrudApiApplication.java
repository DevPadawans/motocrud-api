package com.devpadawans.motocrudapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

@SpringBootApplication(scanBasePackages = {"com.devpadawans.motocrudapi"} , exclude = { DataSourceAutoConfiguration.class })
@EnableTransactionManagement
@EnableJpaRepositories("com.devpadawans.motocrudapi.repository")
//@EntityScan("com.devpadawans.motocrudapi")
//@EnableJpaRepositories("com.devpadawans.motocrudapi")
public class MotocrudApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MotocrudApiApplication.class, args);
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}

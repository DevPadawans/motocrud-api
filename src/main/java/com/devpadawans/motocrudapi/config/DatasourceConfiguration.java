package com.devpadawans.motocrudapi.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@Profile("localdev")
public class DatasourceConfiguration {

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Bean
    public DataSource dataSource() throws SQLException {
        BasicDataSource postgresDataSource = new BasicDataSource();
        postgresDataSource.setDriverClassName("org.postgresql.Driver");
        postgresDataSource.setUsername(this.username);
        postgresDataSource.setPassword(this.password);
        postgresDataSource.setUrl(this.url);
        return postgresDataSource;
    }

}

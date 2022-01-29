package com.devpadawans.motocrudapi.config;

import com.zaxxer.hikari.HikariDataSource;
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

    @Value("${db.validationQuery}")
    private String validationQuery;

    @Value("${db.maximumPoolSize}")
    private Integer maximumPoolSize;

    @Value("${db.minimumIdle}")
    private Integer minimumIdle;

    @Value("${db.connectionTimeout}")
    private Long connectionTimeout;

    @Value("${db.idleTimeout}")
    private Long idleTimeout;

    @Value("${db.maxLifetime}")
    private Long maxLifetime;

    @Bean
    public DataSource dataSource() throws SQLException {
        BasicDataSource postgresDataSource = new BasicDataSource();
        postgresDataSource.setDriverClassName("org.postgresql.Driver");
        postgresDataSource.setUsername(this.username);
        postgresDataSource.setPassword(this.password);
        postgresDataSource.setUrl(this.url);

        var hikariDataSource = new HikariDataSource();
        hikariDataSource.setDataSource(postgresDataSource);
        hikariDataSource.setConnectionTestQuery(this.validationQuery);
        hikariDataSource.setConnectionTimeout(this.connectionTimeout);
        hikariDataSource.setMinimumIdle(this.minimumIdle);
        hikariDataSource.setMaximumPoolSize(this.maximumPoolSize);
        hikariDataSource.setIdleTimeout(this.idleTimeout);
        hikariDataSource.setMaxLifetime(this.maxLifetime);
        hikariDataSource.setSchema(this.username);

        return hikariDataSource;
    }

}

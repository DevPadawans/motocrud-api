package com.devpadawans.motocrudapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("db")
public class PostgresProperty {

    private Hibernate hibernate;
    private String packagesScan;
    private String database;
    private String hostname;
    private String username;
    private String password;
    private String driver;
    private Integer port;
    private String url;

    @Getter
    @Setter
    public static class Hibernate {

        private Ejb ejb;
        private String dialect;
        private Boolean showSql;
        private Boolean formatSql;
        private String ddlAuto = "none";
        private Boolean nonContextualCreation;
        private Boolean useJdbcMetadataDefaults;

        @Getter
        @Setter
        public static class Ejb {
            private String namingStrategy;
        }
    }
}

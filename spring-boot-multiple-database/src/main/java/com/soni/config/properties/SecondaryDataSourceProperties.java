package com.soni.config.properties;

import com.zaxxer.hikari.HikariConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("spring.datasource.secondary")
@Data
public class SecondaryDataSourceProperties extends HikariConfig {
    private String hbm2ddl;
    private String dialect;
    private String showSql;
}

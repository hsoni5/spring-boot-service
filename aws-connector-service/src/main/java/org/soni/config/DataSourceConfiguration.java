package org.soni.config;


import lombok.extern.log4j.Log4j2;
import org.soni.dto.DatabaseProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@Log4j2
public class DataSourceConfiguration{
    @Value("${spring.datasource.url}")
    private String databaseUrl;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.secretKey}")
    private String secretKey;

    @Bean
    @Primary
    public DataSource getDataSource(SecretManagerAwsConfig secretManagerAwsConfig){
        DatabaseProperties databaseProperties = secretManagerAwsConfig.fetchSecretProperties(secretKey, DatabaseProperties.class);
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(databaseUrl)
                .append(databaseProperties.getHost())
                .append(":")
                .append(databaseProperties.getPort())
                .append("/").append(databaseProperties.getDbname());
        dataSourceBuilder.url(stringBuilder.toString());
        dataSourceBuilder.driverClassName(driverClassName);
        dataSourceBuilder.username(databaseProperties.getUsername());
        dataSourceBuilder.password(databaseProperties.getPassword());
        return dataSourceBuilder.build();
    }
}
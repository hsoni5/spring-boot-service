package com.soni.config.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Data
@Configuration
@Primary
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class MongoDbProperties extends MongoProperties {
    public static final String MONGO_TEMPLATE = "mongoTemplate";
    private String socketTimeout;
    private String connectTimeout;
}

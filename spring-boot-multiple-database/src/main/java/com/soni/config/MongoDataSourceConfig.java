package com.soni.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.soni.config.properties.MongoDbProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static com.soni.config.properties.MongoDbProperties.MONGO_TEMPLATE;


@Configuration
@EnableTransactionManagement
@EnableMongoRepositories(basePackages = "com.soni",
        mongoTemplateRef = MONGO_TEMPLATE)
@Log4j2
public class MongoDataSourceConfig {
    private final MongoDbProperties mongoDbProperties;
    @Autowired
    public MongoDataSourceConfig(MongoDbProperties mongoDbProperties){
        this.mongoDbProperties =mongoDbProperties;
    }
    @Primary
    @Bean(name = MONGO_TEMPLATE)
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(), mongoDbProperties.getDatabase());
    }
    @Primary
    @Bean
    public MongoClient mongoClient() throws IOException {
        log.info("Creating mongo client. Socket timeout: {}, request timeout: {}",
                mongoDbProperties.getSocketTimeout(), mongoDbProperties.getConnectTimeout()
        );
      //  MongoCredential credential = MongoCredential.createCredential(mongoDbProperties.getUsername(), mongoDbProperties.getDatabase(), mongoDbProperties.getPassword());

        MongoClientSettings settings = MongoClientSettings.builder()
               // .credential(credential)
                .applyToSocketSettings(builder -> builder.readTimeout(Integer.parseInt(mongoDbProperties.getSocketTimeout()), TimeUnit.MILLISECONDS).connectTimeout(Integer.parseInt(mongoDbProperties.getConnectTimeout()), TimeUnit.MILLISECONDS))
                .applyToClusterSettings(builder ->
                        builder.hosts(Arrays.asList(new ServerAddress(mongoDbProperties.getHost(), mongoDbProperties.getPort()))))
                .build();

        return MongoClients.create(settings);
    }

}

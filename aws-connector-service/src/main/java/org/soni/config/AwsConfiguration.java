package org.soni.config;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.InstanceProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

@Configuration
@Log4j2
public class AwsConfiguration{

    @Value("${spring.profiles.active}")
    public String profile;
    Region region = Region.EU_WEST_1;

    private static final String LOCAL = "local";

    @Bean
    public S3Client s3Client(){
        S3Client s3Client = S3Client.builder()
                .region(region)
                .credentialsProvider(awsCredentialsProvider())
                .build();
        log.info("Created s3Client bean");
        return s3Client;
    }

    @Bean
    public SecretsManagerClient secretsManagerClient(){
        SecretsManagerClient secretsClient = SecretsManagerClient.builder()
                .credentialsProvider(awsCredentialsProvider())
                .region(region)
                .build();
        log.info("Created secretsClient bean");
        return secretsClient;
    }

    public AwsCredentialsProvider awsCredentialsProvider(){
        AwsCredentialsProvider credentialsProvider = null;
        if(LOCAL.equalsIgnoreCase(profile)){
            credentialsProvider = ProfileCredentialsProvider.create();
        }else{
            credentialsProvider = InstanceProfileCredentialsProvider.create();
        }
        return credentialsProvider;
    }
}
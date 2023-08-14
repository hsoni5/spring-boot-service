package org.soni.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;

@Component
@Configuration
@Log4j2
public class SecretManagerAwsConfig{
    @Autowired
    private SecretsManagerClient secretsManagerClient;

    public <D> D fetchSecretProperties(String secretKey, Class<D> outClass){
        log.info("Started fetching : {} from secret manager:fetchSecretProperties()", secretKey);
        try{
            GetSecretValueRequest valueRequest = GetSecretValueRequest.builder().secretId(secretKey).build();
            GetSecretValueResponse valueResponse = secretsManagerClient.getSecretValue(valueRequest);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(valueResponse.secretString(), outClass);
        }catch(SecretsManagerException exception){
            log.error("Error while fetching: {} key from aws secret manager: {}", secretKey, exception.awsErrorDetails().errorMessage());
        }catch(JsonProcessingException exception){
            log.error("Error occurred during: {} secret key json mapping: {}", secretKey, exception);
        }
        return null;
    }
}
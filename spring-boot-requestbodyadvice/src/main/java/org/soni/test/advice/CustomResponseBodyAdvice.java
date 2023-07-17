package org.soni.test.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.soni.test.secure.Encryption;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

@RestControllerAdvice
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    private static final String SECURE_KEY = "secretKey";

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        System.out.println("In supports() method of " + getClass().getSimpleName());

        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        System.out.println("In supports() method of " + getClass().getSimpleName());
        if (Objects.requireNonNull(methodParameter.getMethod()).isAnnotationPresent(SecretResponse.class)) {
            SecretResponse secretResponse = methodParameter.getMethod().getAnnotation(SecretResponse.class);
            if (secretResponse.encode()) {
                try {
                ObjectMapper objectMapper = new ObjectMapper();
                String value = objectMapper.writeValueAsString(body);
                    System.out.println("Encrypted body"+ value);

                    return new Encryption().encryptString(value, SECURE_KEY);
                } catch (JsonProcessingException | UnsupportedEncodingException e ) {
                    e.printStackTrace();
                }
            }
        }

        return body;
    }
}

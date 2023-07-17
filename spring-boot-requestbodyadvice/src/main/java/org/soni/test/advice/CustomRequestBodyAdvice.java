package org.soni.test.advice;

import org.apache.commons.io.IOUtils;
import org.soni.test.secure.Encryption;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@RestControllerAdvice
public class CustomRequestBodyAdvice implements RequestBodyAdvice {
    private static final String SECURE_KEY = "secretKey";

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        // Return true if you want to intercept the request body for this specific method parameter.
        // You can also customize based on targetType or converterType if needed.
        System.out.println("In supports() method of " + getClass().getSimpleName());
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter,
                                           Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        // This method is called before the request body is read and processed by the controller method.
        // You can modify the inputMessage or return a different HttpInputMessage instance.
        // If you don't need to modify the request body, simply return the inputMessage as is.
        System.out.println("In beforeBodyRead() method of " + getClass().getSimpleName());
        if (Objects.requireNonNull(parameter.getMethod()).isAnnotationPresent(SecretResponse.class)) {
            SecretResponse secretResponse = parameter.getMethod().getAnnotation(SecretResponse.class);
            if (secretResponse.decode()) {
                return new HttpInputMessage() {
                    @Override
                    public InputStream getBody() throws IOException {
                        String bodyStr = IOUtils.toString(inputMessage.getBody(), StandardCharsets.UTF_8);
                        try {
                            bodyStr = new Encryption().decryptString(bodyStr, SECURE_KEY);
                            System.out.println("Decrypted body"+ bodyStr);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        return IOUtils.toInputStream(bodyStr);
                    }

                    @Override
                    public HttpHeaders getHeaders() {
                        return inputMessage.getHeaders();
                    }
                };

            }
        }
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
                                Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        // This method is called after the request body is read and processed by the controller method.
        // You can modify the body object or return a different object if needed.
        // If you don't need to modify the object, simply return the body as is.
        System.out.println("In beforeBodyRead() method of " + getClass().getSimpleName());

        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
                                  Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        // This method is called when the request body is empty.
        // You can provide a default value or throw an exception if required.
        // If you don't need any special handling, simply return null.
        System.out.println("In handleEmptyBody() method of " + getClass().getSimpleName());

        return body;
    }
}
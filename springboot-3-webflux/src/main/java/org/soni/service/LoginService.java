package org.soni.service;

import org.soni.dto.LoginRequest;
import org.soni.dto.Token;
import reactor.core.publisher.Mono;

public interface LoginService {

    Mono<Token> login(LoginRequest loginRequest);

    boolean validate(Token token);

    Mono<Void> logout(Token token);

}
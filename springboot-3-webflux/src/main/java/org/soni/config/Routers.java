package org.soni.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.soni.dto.CreateUserData;
import org.soni.dto.LoginRequest;
import org.soni.dto.Token;
import org.soni.dto.UserData;
import org.soni.service.LoginService;
import org.soni.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
@OpenAPIDefinition(info = @Info(title = "Swagger Demo", version = "1.0", description = "Documentation APIs v1.0"))
public class Routers {

    @Bean
    public RouterFunction<ServerResponse> route(UserService userService, LoginService loginService) {
        return RouterFunctions
                //Secured endpoints
                .route(GET("/secured/users/{id}").and(accept(APPLICATION_JSON)), request -> {
                    Mono<UserData> userDataMono = userService.getEmployee(request.pathVariable("id"));
                    return ServerResponse.ok().body(userDataMono, UserData.class);
                })
                .andRoute(GET("/secured/users").and(accept(APPLICATION_JSON)), request -> {
                    Flux<UserData> userDataFlux = userService.getAll();
                    return ServerResponse.ok().body(userDataFlux, UserData.class);
                })
                .andRoute(POST("/secured/users").and(accept(APPLICATION_JSON)), request -> {
                    Mono<CreateUserData> monoBody = request.bodyToMono(CreateUserData.class);
                    Mono<UserData> userDataMono = monoBody.flatMap(userService::create);
                    return ServerResponse.ok().body(userDataMono, UserData.class);
                })
                .andRoute(DELETE("/secured/users/{id}").and(accept(APPLICATION_JSON)), request -> {
                    Mono<UserData> userDataMono = userService.delete(request.pathVariable("id"));
                    return ServerResponse.ok().body(userDataMono, UserData.class);
                })
                .andRoute(PUT("/secured/users").and(accept(APPLICATION_JSON)), request -> {
                    Mono<UserData> monoBody = request.bodyToMono(UserData.class);
                    Mono<UserData> userDataMono = monoBody.flatMap(userService::update);
                    return ServerResponse.ok().body(userDataMono, UserData.class);
                })
                .andRoute(PUT("/secured/create/{n}").and(accept(APPLICATION_JSON)), request -> {
                    userService.createUsersBulk(Integer.parseInt(request.pathVariable("n")));
                    return ServerResponse.ok().build();
                })
                .filter(new ExampleHandlerFilterFunction(loginService))
                //Un-Secured Login/Logout endpoints
                .andRoute(POST("/secured/login").and(accept(APPLICATION_JSON)), request -> {
                    Mono<LoginRequest> monoBody = request.bodyToMono(LoginRequest.class);
                    Mono<Token> tokenMono = monoBody.flatMap(loginService::login);
                    return ServerResponse.ok().body(tokenMono, Token.class);
                })
                .andRoute(POST("/secured/logout").and(accept(APPLICATION_JSON)), request -> {
                    Mono<Token> monoBody = request.bodyToMono(Token.class);
                    Mono<Void> voidMono = monoBody.flatMap(loginService::logout);
                    return ServerResponse.ok().body(voidMono, Void.class);
                });
    }
}

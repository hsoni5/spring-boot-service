package org.soni.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.soni.controller.PostHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Swagger Demo", version = "1.0", description = "Documentation APIs v1.0"))

public class PostRouters {
    @Bean
    RouterFunction<ServerResponse> routes(PostHandler postHandler) {
        return
                nest(path("/api/posts"),
                        nest(accept(MediaType.APPLICATION_JSON),
                                route(method(HttpMethod.GET), postHandler::listPosts)
                                        .andRoute(DELETE("/{id}"), postHandler::deletePost)
                                        .andRoute(POST("/"), postHandler::savePost)
                                        .andRoute(PUT("/{id}"), postHandler::updatePost)));

    }
}

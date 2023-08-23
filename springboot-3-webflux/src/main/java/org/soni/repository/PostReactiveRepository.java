package org.soni.repository;

import org.soni.domain.Post;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PostReactiveRepository extends ReactiveCrudRepository<Post, String> {
    Mono<Boolean> existsByTitle(String title);
}
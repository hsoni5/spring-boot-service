package org.soni.service;

import org.soni.dto.CreateUserData;
import org.soni.dto.UserData;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<UserData> create(CreateUserData createUserData);

    Mono<UserData> getEmployee(String id);

    Flux<UserData> getAll();

    Mono<UserData> update(UserData employee);

    Mono<UserData> delete(String id);

    void createUsersBulk(Integer n);

    Publisher<UserData> getAllStream();

}
package com.hans.rboard.repository;

import com.hans.rboard.dto.ReqAddUser;
import com.hans.rboard.entity.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends R2dbcRepository<User, Long> {
    Mono<User> findUserEntityByUid(Long uid);
    Mono<Integer> deleteUserEntityByUid(Long uid);
    Mono<User> findUserEntityByUserId(String userId);

    @Query("select * from users where name = :name")
    Flux<User> findByName(String name);
}

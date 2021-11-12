package com.hans.rboard.service;

import com.hans.dto.ErrCode;
import com.hans.dto.ResObj;
import com.hans.rboard.entity.User;
import com.hans.rboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public Mono<?> findAll() {
        return userRepository.findAll().
                collectList().flatMap(ResObj::success);
    }

    public Mono<?> addUser(User user) {

        return Mono.just(userRepository::save).flatMap(ResObj::success);
    }
}

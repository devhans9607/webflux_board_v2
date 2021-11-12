package com.hans.rboard.service;

import com.hans.rboard.entity.User;
import com.hans.rboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }
}

package com.hans.rboard.web;

import com.hans.dto.ErrCode;
import com.hans.dto.ResObj;
import com.hans.rboard.entity.User;
import com.hans.rboard.repository.UserRepository;
import com.hans.rboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public Mono<?> findAllUser() {
        return userService.findAll();
    }

    @PostMapping("/user")
    public Mono<?> addUser(User user) {
        return userService.addUser(user);
    }

//    @GetMapping("/success")
//    public Mono<?> success() {
//        return ResObj.success();
//    }
//
//    @GetMapping("/fail")
//    public Mono<?> fail() {
//        return ResObj.failure(ErrCode.NO_DATA);
//    }
}

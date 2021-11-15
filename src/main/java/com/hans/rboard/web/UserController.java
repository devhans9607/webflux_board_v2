package com.hans.rboard.web;

import com.hans.rboard.dto.*;
import com.hans.rboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public Mono<?> findAllUser() {
        return userService.findAll();
    }

    @PostMapping("/user/add")
    public Mono<?> addUser(@RequestBody Mono<ReqAddUser> user) {
        return userService.addUser(user);
    }

    @PostMapping("/user/get")
    public Mono<?> findUser(@RequestBody Mono<ReqGetUser> req) {
        return userService.findByUid(req);
    }

    @PostMapping("/user/remove")
    public Mono<?> removeUser(@RequestBody Mono<ReqRemoveUser> req) {
        return userService.removeByUid(req);
    }

    @PostMapping("/user/edit")
    public Mono<?> editUser(@RequestBody Mono<ReqEditUser> req) {
        return userService.editUser(req);
    }

    @PostMapping("/user/sign-in")
    public Mono<?> signIn(@RequestBody Mono<ReqSignIn> req) {
        return userService.signIn(req);
    }

    @PostMapping("/user/sign-up")
    public Mono<?> signUp(@RequestBody Mono<ReqSignUp> req) {
        return userService.signUp(req);
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

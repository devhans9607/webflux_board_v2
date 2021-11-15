package com.hans.rboard.service;

import com.hans.dto.ErrCode;
import com.hans.dto.ResObj;
import com.hans.rboard.dto.user.*;
import com.hans.rboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.Objects;

// TODO : ERROR HANDLER
//      : Password Encoder

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public Mono<?> findAll() {
        return userRepository.findAll()
                .collectList()
                .flatMap(ResObj::success)
                .switchIfEmpty(ResObj.failure(ErrCode.NO_DATA));
    }

    // Deprecated
    public Mono<?> addUser(Mono<ReqAddUser> req) {
        return req.map(ReqAddUser::mapper)
                .flatMap(userRepository::save)
                .flatMap(ResObj::success);
    }

    // Deprecated
    public Mono<?> findByUid(Mono<ReqGetUser> req) {
        return req
                .flatMap(r -> userRepository.findUserEntityByUid(r.getUid()))
                .flatMap(ResObj::success)
                .switchIfEmpty(ResObj.failure(ErrCode.NO_USER));
    }

    public Mono<?> removeByUid(Mono<ReqRemoveUser> req) {
        return req.flatMap(r -> userRepository.deleteUserEntityByUid(r.getUid()))
                .flatMap(result -> (result == 0)
                                        ? ResObj.failure(ErrCode.NO_USER)
                                        : ResObj.success(new ResRemoveUser(result)));
    }

    // TODO : Using 'then' -> how to handle existing error?
    public Mono<?> editUser(Mono<ReqEditUser> req) {
        return req.map(ReqEditUser::mapper)
                .flatMap(r -> userRepository.findUserEntityByUid(r.getUid())
                                            .then(userRepository.save(r))
                                            .flatMap(ResObj::success)
                                            .switchIfEmpty(ResObj.failure(ErrCode.NO_USER)));
    }

    public Mono<?> signIn(Mono<ReqSignIn> req) {
        return req.flatMap(r -> userRepository.findUserEntityByUserId(r.getUserId())
                                    .flatMap(u -> (u.getUserPwd().equals(r.getUserPwd()))
                                                        ? ResObj.success(new ResSignIn(true, u.getUserId(), u.getValid()))
                                                        : ResObj.failure(ErrCode.WRONG_PASSCODE, new ResSignIn(false, u.getUserId(), u.getValid())))
                                    .switchIfEmpty(ResObj.failure(ErrCode.NO_USER)));
    }

    public Mono<?> signUp(Mono<ReqSignUp> req) {
        return req.flatMap(r -> userRepository.findUserEntityByUserId(r.getUserId())
                                                .switchIfEmpty(Mono.just(ReqSignUp.mapper(r)))
                                                .flatMap(v -> Objects.isNull(v.getUid())
                                                        ? userRepository.save(v).flatMap(ResObj::success)
                                                        : ResObj.failure(ErrCode.EXISTING_USER))
                                                .switchIfEmpty(ResObj.failure(ErrCode.UNKNOWN_ERROR)));
    }
}

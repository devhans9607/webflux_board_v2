package com.hans.rboard.dto.user;

import com.hans.rboard.entity.User;
import lombok.Data;

@Data
public class ReqSignUp {
    String userId;
    String userPwd;
    String name;

    public static User mapper(ReqSignUp req) {
        return User.builder()
                .name(req.getName())
                .userId(req.getUserId())
                .userPwd(req.getUserPwd())
                .valid(true)
                .build();
    }
}

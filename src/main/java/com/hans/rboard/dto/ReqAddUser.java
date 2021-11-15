package com.hans.rboard.dto;

import com.hans.rboard.entity.User;
import lombok.Data;

@Data
public class ReqAddUser {
    String userId;
    String userPwd;
    String name;
    Boolean valid;

    public static User mapper(ReqAddUser req) {
        return User.builder()
                .name(req.getName())
                .userId(req.getUserId())
                .userPwd(req.getUserPwd())
                .valid(req.getValid())
                .build();
    }
}

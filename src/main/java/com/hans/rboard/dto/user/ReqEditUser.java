package com.hans.rboard.dto.user;

import com.hans.rboard.entity.User;
import lombok.Data;

@Data
public class ReqEditUser {
    Long uid;
    String userId;
    String userPwd;
    String name;
    Boolean valid;

    public static User mapper(ReqEditUser req) {
        return User.builder()
                .uid(req.getUid())
                .name(req.getName())
                .userId(req.getUserId())
                .userPwd(req.getUserPwd())
                .valid(req.getValid())
                .build();
    }
}

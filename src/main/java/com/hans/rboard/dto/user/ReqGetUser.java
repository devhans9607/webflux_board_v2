package com.hans.rboard.dto.user;

import lombok.Data;
import reactor.core.publisher.Mono;

@Data
public class ReqGetUser {
    Long uid;
}

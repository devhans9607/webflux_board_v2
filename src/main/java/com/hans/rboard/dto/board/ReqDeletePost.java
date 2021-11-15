package com.hans.rboard.dto.board;

import lombok.Data;

@Data
public class ReqDeletePost {
    private Long postId;
    private Long uid;
}

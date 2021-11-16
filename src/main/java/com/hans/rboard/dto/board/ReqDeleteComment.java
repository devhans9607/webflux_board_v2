package com.hans.rboard.dto.board;

import lombok.Data;

@Data
public class ReqDeleteComment {
    private Long commentId;
    private Long uid;
}

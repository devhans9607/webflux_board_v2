package com.hans.rboard.dto.board;

import lombok.Data;

@Data
public class ReqGetPost {
    private Integer pageNum = 1;
    private Integer commentsPerPage = 5;
    private Long postId;
}

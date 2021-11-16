package com.hans.rboard.dto.board;

import com.hans.rboard.entity.Comment;
import lombok.Data;

@Data
public class ReqNewComment {
    private Long postId;
    private String contents;
    private Long uid;

    public static Comment mapper(ReqNewComment req) {
        return Comment.builder()
                .postId(req.getPostId())
                .contents(req.getContents())
                .isDeleted(false)
                .uid(req.getUid())
                .build();
    }
}

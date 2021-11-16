package com.hans.rboard.dto.board;

import com.hans.rboard.entity.Comment;
import lombok.Data;

@Data
public class ReqEditComment {
    private Long commentId;
    private Long postId;
    private Long uid; // commentUid
    private String contents;

    public static Comment mapper(Comment comment, ReqEditComment req) {
        comment.setContents(req.getContents());
        return comment;
    }
}

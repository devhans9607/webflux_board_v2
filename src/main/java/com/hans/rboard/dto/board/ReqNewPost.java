package com.hans.rboard.dto.board;

import com.hans.rboard.entity.Post;
import lombok.Data;

@Data
public class ReqNewPost {
    private String title;
    private String contents;
    private Long uid;

    public static Post mapper(ReqNewPost req) {
        return Post.builder()
                .contents(req.getContents())
                .title(req.getTitle())
                .uid(req.getUid())
                .isDeleted(false)
                .build();
    }
}

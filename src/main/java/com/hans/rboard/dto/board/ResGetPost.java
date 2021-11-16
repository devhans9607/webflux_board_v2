package com.hans.rboard.dto.board;

import com.hans.rboard.entity.Comment;
import com.hans.rboard.entity.CommentWithName;
import com.hans.rboard.entity.Post;
import com.hans.rboard.entity.PostWithName;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import reactor.util.function.Tuple2;

import java.util.List;

@Data
@AllArgsConstructor
public class ResGetPost {
    private List<CommentWithName> pagedComments;
    private PostWithName post;

    public ResGetPost(Tuple2<List<CommentWithName>, PostWithName> tuple2) {
        this.pagedComments = tuple2.getT1();
        this.post = tuple2.getT2();
    }
}

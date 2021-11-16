package com.hans.rboard.repository;

import com.hans.rboard.entity.Comment;
import com.hans.rboard.entity.CommentWithName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentRepository extends R2dbcRepository<Comment, Long> {
    Flux<Comment> findCommentsByPostId(Pageable page, Long postId);

    @Query(value = "select comments.*, users.name from comments inner join users on users.uid = comments.uid where comments.post_id = :postId")
    Flux<CommentWithName> findCommentsByPostIdWithName(Pageable page, Long postId);

    @Modifying
    @Query(value = "update comments set is_deleted = true, deleted_at = current_timestamp where comment_id = :commentId and is_deleted = false")
    Mono<Integer> setIsDeleted(@Param(value = "commentId") Long commentId);

    @Query("select comments.*, users.name from comments inner join users on users.uid = comments.uid where comments.post_id = :postId order by comments.comment_id limit :limit offset :offset")
    Flux<CommentWithName> findByIdWithName(@Param(value = "limit") int limit, @Param(value = "offset") int offset, @Param(value = "postId") Long postId);
}

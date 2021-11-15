package com.hans.rboard.repository;

import com.hans.rboard.entity.Post;
import lombok.NonNull;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Mono;

// TODO: set common where clause?
public interface PostRepository extends R2dbcRepository<Post, Long> {
    @Modifying
    @Query(value = "update posts set is_deleted = true , delete_at = current_timestamp where post_id = :postId and is_deleted = false")
    Mono<Integer> setIsDeleted(@Param(value = "postId") Long postId);

    @Query("select * from posts where post_id = :postId and is_deleted = false")
    @NonNull
    Mono<Post> findById(@Param(value = "postId") @NonNull Long postId);

    // successfully joined user. But still no relation (ORM-less).
    @Query("select posts.*, users.* from posts inner join users on users.uid = posts.uid where posts.post_id = :postId")
    Mono<Post> findByIdTest(@Param(value = "postId") Long postId);
}

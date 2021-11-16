package com.hans.rboard.repository;

import com.hans.rboard.entity.Post;
import com.hans.rboard.entity.PostWithName;
import lombok.NonNull;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
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
    @Query("select posts.*, users.name from posts inner join users on users.uid = posts.uid where posts.post_id = :postId and posts.is_deleted = false")
    Mono<PostWithName> findByIdWithName(@Param(value = "postId") Long postId);

    @Query("select posts.*, users.name from posts inner join users on users.uid = posts.uid where posts.title like :keyword and posts.is_deleted = false order by posts.post_id limit :limit offset :offset")
    Flux<PostWithName> findByTitleContainingWithName(@Param(value = "limit") int limit, @Param(value = "offset") int offset, @Param(value = "keyword") String keyword);
}

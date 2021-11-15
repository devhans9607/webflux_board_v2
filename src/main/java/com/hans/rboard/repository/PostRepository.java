package com.hans.rboard.repository;

import com.hans.rboard.entity.Post;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface PostRepository extends R2dbcRepository<Post, Long> {
}

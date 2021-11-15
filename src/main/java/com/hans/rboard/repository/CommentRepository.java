package com.hans.rboard.repository;

import com.hans.rboard.entity.Comment;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface CommentRepository extends R2dbcRepository<Comment, Long> {
}

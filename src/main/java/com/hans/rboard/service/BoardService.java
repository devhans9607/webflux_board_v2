package com.hans.rboard.service;

import com.hans.rboard.repository.CommentRepository;
import com.hans.rboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class BoardService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public Mono<?> getPost() {
        return null;
    }

    public Mono<?> newPost() {
        return null;
    }

    public Mono<?> editPost() {
        return null;
    }

    public Mono<?> deletePost() {
        return null;
    }

    public Mono<?> pagedPosts() {
        return null;
    }

    public Mono<?> newComment() {
        return null;
    }

    public Mono<?> editComment() {
        return null;
    }

    public Mono<?> deleteComment() {
        return null;
    }
}

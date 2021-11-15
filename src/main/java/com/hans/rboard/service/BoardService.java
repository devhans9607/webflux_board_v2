package com.hans.rboard.service;

import com.hans.dto.ErrCode;
import com.hans.dto.ResObj;
import com.hans.rboard.dto.board.*;
import com.hans.rboard.entity.Post;
import com.hans.rboard.entity.User;
import com.hans.rboard.repository.CommentRepository;
import com.hans.rboard.repository.PostRepository;
import com.hans.rboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final UserRepository userRepository;

    public Mono<?> getPostSimple(Mono<ReqGetSimplePost> req) {
//        return req.flatMap(r -> postRepository.findById(r.getPostId())).flatMap(ResObj::success).switchIfEmpty(ResObj.failure(ErrCode.NO_POST));
        return req.flatMap(r -> postRepository.findByIdWithName(r.getPostId()))
                .flatMap(ResObj::success)
                .switchIfEmpty(ResObj.failure(ErrCode.NO_POST));
    }

    // create pageable
    // get post by postId (with name)
    // get comments by postId (with name / pagination)
    // map to res dto
    public Mono<?> getPost(Mono<ReqGetPost> req) {
        return req.map(r -> {
            Pageable pageable = PageRequest.of(r.getPageNum()-1, r.getCommentsPerPage(), Sort.Direction.ASC, "comment_id");

            return null;
        });
    }

    public Mono<?> newPost(Mono<ReqNewPost> req) {
        return req.map(ReqNewPost::mapper)
                .flatMap(post -> userRepository.findUserEntityByUid(post.getUid())
                                                .flatMap(u -> u.getUid().equals(post.getUid())
                                                                    ? postRepository.save(post).flatMap(ResObj::success)
                                                                    : ResObj.failure(ErrCode.UNKNOWN_ERROR)))
                                                .switchIfEmpty(ResObj.failure(ErrCode.NO_USER));
    }

    public Mono<?> editPost(Mono<ReqEditPost> req) {
        // map
        // get post entity
            // if null -> fail
            // else check if user is equal
                // true -> save post
                // false -> fail
        return req.flatMap(r -> postRepository.findById(r.getPostId())
                    .flatMap(p -> p.getUid().equals(r.getUid())
                            ? Mono.just(p)
                                    .map(m -> ReqEditPost.mapper(p, r))
                                    .flatMap(postRepository::save)
                                    .flatMap(ResObj::success)
                                    .switchIfEmpty(ResObj.failure(ErrCode.UNKNOWN_ERROR))
                            : ResObj.failure(ErrCode.UID_MISMATCH))
                    .switchIfEmpty(ResObj.failure(ErrCode.NO_POST)));
    }

    // TODO: check if already deleted?
    // 역대급 가독성..
    public Mono<?> deletePost(Mono<ReqDeletePost> req) {
        return req.flatMap(r -> postRepository.findById(r.getPostId())
                                    .flatMap(v -> (v.getUid().equals(r.getUid()))
                                                    ? postRepository.setIsDeleted(r.getPostId())
                                                                    .flatMap(f -> (f == 0)
                                                                                    ? ResObj.failure(ErrCode.UNKNOWN_ERROR, new ResDeletePost(r.getPostId(), f))
                                                                                    : ResObj.success(new ResDeletePost(r.getPostId(), f)))
                                                                    .switchIfEmpty(ResObj.failure(ErrCode.UNKNOWN_ERROR))
                                                    : ResObj.failure(ErrCode.UID_MISMATCH))
                                    .switchIfEmpty(ResObj.failure(ErrCode.NO_POST)));
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

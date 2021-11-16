package com.hans.rboard.service;

import com.hans.dto.ErrCode;
import com.hans.dto.ResObj;
import com.hans.rboard.dto.board.*;
import com.hans.rboard.entity.*;
import com.hans.rboard.repository.CommentRepository;
import com.hans.rboard.repository.PostRepository;
import com.hans.rboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.ArrayList;
import java.util.List;

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
        return req.flatMap(this::postResZipper)
                .map(ResGetPost::new)
                .flatMap(ResObj::success)
                .switchIfEmpty(ResObj.failure(ErrCode.UNKNOWN_ERROR));

//        return req.flatMap(r -> {
//                    // pageable not really necessary but, for temporally keeping for exception handling
//                    Pageable pageable = PageRequest.of(r.getPageNum()-1, r.getCommentsPerPage(), Sort.Direction.ASC, "commentId");
//                    Mono<PostWithName> post =  postRepository.findByIdWithName(r.getPostId());
//                    Mono<List<CommentWithName>> comments =  commentRepository.findByIdWithName(pageable.getPageSize(), pageable.getOffset(), r.getPostId()).collectList();
//                    return Mono.zip(comments, post);
//                }).map(v -> new ResGetPost(v.getT1(), v.getT2()))
//                .flatMap(ResObj::success)
//                .switchIfEmpty(ResObj.failure(ErrCode.UNKNOWN_ERROR));
    }

    Mono<Tuple2<List<CommentWithName>, PostWithName>> postResZipper(ReqGetPost r) {
        return Mono.zip(
                commentRepository.findByIdWithName(r.getCommentsPerPage(), (r.getPageNum() - 1) * r.getCommentsPerPage(), r.getPostId()).collectList(),
                postRepository.findByIdWithName(r.getPostId())
        );
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

    // TODO: check if already deleted? -> In query?
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

    public Mono<?> pagedPosts(Mono<ReqGetPagedPosts> req) {
        return req.flatMap(r -> postRepository.findByTitleContainingWithName(r.getPostPerPage(), (r.getPageNum() - 1) * r.getPostPerPage(), "%" + r.getKeyword() + "%").collectList()
                                                .map(v -> new ResGetPagedPosts(v, r.getKeyword()))
                                                .flatMap(ResObj::success))
                                                .switchIfEmpty(ResObj.failure(ErrCode.NO_POST));
    }

    public Mono<?> newComment(Mono<ReqNewComment> req) {
        return req.map(ReqNewComment::mapper)
                .flatMap(commentRepository::save)
                .flatMap(ResObj::success)
                .switchIfEmpty(ResObj.failure(ErrCode.UNKNOWN_ERROR));
    }

    public Mono<?> editComment(Mono<ReqEditComment> req) {
        return req.flatMap(r -> commentRepository.findById(r.getCommentId())
                                    .flatMap(c -> c.getUid().equals(r.getUid())
                                                ? Mono.just(c)
                                                        .map(m -> ReqEditComment.mapper(c, r))
                                                        .flatMap(commentRepository::save)
                                                        .flatMap(ResObj::success)
                                                        .switchIfEmpty(ResObj.failure(ErrCode.UNKNOWN_ERROR))
                                                : ResObj.failure(ErrCode.UID_MISMATCH))
                                    .switchIfEmpty(ResObj.failure(ErrCode.NO_COMMENT)));
    }

    public Mono<?> deleteComment(Mono<ReqDeleteComment> req) {
        return req.flatMap(r -> commentRepository.findById(r.getCommentId())
                                    .flatMap(v -> (v.getUid().equals(r.getUid()))
                                                    ? commentRepository.setIsDeleted(r.getCommentId())
                                                                        .flatMap(f -> (f == 0)
                                                                                    ? ResObj.failure(ErrCode.UNKNOWN_ERROR)
                                                                                    : ResObj.success())
                                                                        .switchIfEmpty(ResObj.failure(ErrCode.UNKNOWN_ERROR))
                                                    : ResObj.failure(ErrCode.UID_MISMATCH))
                                    .switchIfEmpty(ResObj.failure(ErrCode.NO_COMMENT)));
    }
}

package com.hans.rboard.web;

import com.hans.rboard.dto.board.*;
import com.hans.rboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/post/simple/get")
    public Mono<?> getSimplePost(@RequestBody Mono<ReqGetSimplePost> req) {
        return boardService.getPostSimple(req);
    }
    
    @PostMapping("/post/get")
    public Mono<?> getPost(@RequestBody Mono<ReqGetPost> req) {
        return boardService.getPost(req);
    }

    @PostMapping("/post/new")
    public Mono<?> newPost(@RequestBody Mono<ReqNewPost> req) {
        return boardService.newPost(req);
    }

    @PostMapping("/post/edit")
    public Mono<?> editPost(@RequestBody Mono<ReqEditPost> req) {
        return boardService.editPost(req);
    }

    @PostMapping("/post/remove")
    public Mono<?> deletePost(@RequestBody Mono<ReqDeletePost> req) {
        return boardService.deletePost(req);
    }

    @PostMapping("/post/paged")
    public Mono<?> pagedPosts() {
        return boardService.pagedPosts();
    }

    @PostMapping("/comment/new")
    public Mono<?> newComment() {
        return boardService.newComment();
    }

    @PostMapping("/comment/edit")
    public Mono<?> editComment() {
        return boardService.editComment();
    }

    @PostMapping("/comment/remove")
    public Mono<?> deleteComment() {
        return boardService.deleteComment();
    }
    
}

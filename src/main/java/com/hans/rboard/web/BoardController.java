package com.hans.rboard.web;

import com.hans.rboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BoardController {
    private final BoardService boardService;
    
    @PostMapping("/post/get")
    public Mono<?> getPost() {
        return boardService.getPost();
    }

    @PostMapping("/post/new")
    public Mono<?> newPost() {
        return boardService.newPost();
    }

    @PostMapping("/post/edit")
    public Mono<?> editPost() {
        return boardService.editPost();
    }

    @PostMapping("/post/remove")
    public Mono<?> deletePost() {
        return boardService.deletePost();
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

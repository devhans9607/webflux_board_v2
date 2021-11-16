package com.hans.rboard.dto.board;

import com.hans.rboard.entity.PostWithName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResGetPagedPosts {
    private List<PostWithName> posts;
    private String keyword;
}

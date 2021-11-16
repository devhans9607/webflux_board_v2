package com.hans.rboard.dto.board;

import lombok.Data;

@Data
public class ReqGetPagedPosts {
    private int pageNum;
    private int postPerPage;
    private String keyword;
}

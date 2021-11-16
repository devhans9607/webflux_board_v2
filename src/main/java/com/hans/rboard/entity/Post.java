package com.hans.rboard.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import javax.xml.stream.events.Comment;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table("posts")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
public class Post {
    @Id
    @Column("post_id")
    private Long postId;

    @Column("uid")
    private Long uid;
//    private User user;

    @Column("title")
    private String title;

    @Column("contents")
    private String contents;

    @Column("created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column("updated_at")
    private LocalDateTime updatedAt;

    @Column("delete_at")
    private LocalDateTime deletedAt;

    @Column("is_deleted")
    private Boolean isDeleted;
//
//    @Column("userid")
//    private String userId;

//    @JsonIgnore
//    private List<Comment> comments;
}

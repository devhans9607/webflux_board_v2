package com.hans.rboard.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class PostWithName extends Post {
    @Column("name")
    private String userName;
//
//    @Column("userid")
//    private String userId;

//    @JsonIgnore
//    private List<Comment> comments;
}

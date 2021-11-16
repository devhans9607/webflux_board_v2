package com.hans.rboard.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommentWithName extends Comment {
    @Column("name")
    private String userName;
}

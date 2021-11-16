package com.hans.rboard.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;

@EqualsAndHashCode(callSuper = true)
@Data
public class PostWithName extends Post {
    @Column("name")
    private String userName;
}

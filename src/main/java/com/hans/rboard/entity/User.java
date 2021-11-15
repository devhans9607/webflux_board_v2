package com.hans.rboard.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table("users")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
public class User {

    // When your database has an auto-increment column for the ID column, the generated value gets set in the entity after inserting it into the database.
    // https://docs.spring.io/spring-data/r2dbc/docs/current-SNAPSHOT/reference/html/#reference

    @Id
    @Column(value = "uid")
    private Long uid;

    @Column("userid")
    private String userId;

    @Column("pwd")
    private String userPwd;

    @Column("name")
    private String name; //name 수저ㅏ

    @Column("valid")
    private Boolean valid;
}

package com.hans.rboard.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table("users")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @Id
    @Column("uid")
    private Integer uid;

    @Column("userid")
    private String userId;

    @Column("pwd")
    private String userPwd;

    @Column("name")
    private String name; //name 수저ㅏ

    @Column("valid")
    private Boolean valid;

//    @JsonIgnore
//    public User(ReqSignInDto dto) {
//
//    }
}

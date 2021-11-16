package com.hans.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrCode {
    SUCCESS (1000, "Success"),

    INVALID_PARAMETER (2000, "Invalid parameter"),
    NO_SUBJECT(2001, "Subject is empty"),
    NO_DATA(2002, "No data"),
    UNZIP_ERROR(2003, "Invalid Zip"),

    //login

    WRONG_PASSCODE (2010, "Wrong password"),
    NO_USER(2011, "UserID not found"),
    EXISTING_USER(2012, "user Id already taken"),
    ALREADY_DISABLED(2013, "user is already disabled"),

    //post

    NO_POST(2020, "no post found by postId"),
    UID_MISMATCH(2021, "Uid different from original post."),
    NO_COMMENT(2022, "no comment found by commentId"),

    UNKNOWN_ERROR (9001, "Unknown Error"),
    END(9999, "ErrCode End");

    final int code;
    final String desc;
}
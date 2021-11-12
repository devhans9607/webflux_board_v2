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
    
    UNKNOWN_ERROR (9001, "Unknown Error"),
    END(9999, "ErrCode End");

    final int code;
    final String desc;
}
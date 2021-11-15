package com.hans.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import reactor.core.publisher.Mono;

// TODO: Is this way of custom response be disadvantaged for use of mono/flux?

@Data
public class ResObj<T> {
    int code;
    String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    T content;

    ResObj() {
        setErrCode(ErrCode.SUCCESS);
    }

    ResObj(ErrCode errCode) {
        setErrCode(errCode);
    }

    ResObj(ErrCode errCode, T content) {
        setErrCode(errCode);
        this.content = content;
    }

    ResObj(int code, String msg) {
        this.code = code;
        this.message = msg;
    }

//    ResponseInfo(int code, String message, T data) {
//        this.code = code;
//        this.message = message;
//        this.data = data;
//    }

    void setErrCode(ErrCode errCode) {
        this.code = errCode.getCode();
        this.message = errCode.getDesc();
    }

    public void setErrCode(int code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public static <T> Mono<ResObj<T>> success() {
        return Mono.just(new ResObj<>(ErrCode.SUCCESS));
    }

    public static <T> Mono<ResObj<T>> success(T monoData) {
        return Mono.just(new ResObj<>(ErrCode.SUCCESS, monoData));
    }

    // TODO: Consider also accepting Flux?

    public static <T> Mono<ResObj<T>> failure(ErrCode errCode) {
        return Mono.just(new ResObj<>(errCode));
    }

    public static <T> Mono<ResObj<T>> failure(int code, String msg) {
        return Mono.just(new ResObj<>(code, msg));
    }

    public static <T> Mono<ResObj<T>> failure() {
        return Mono.just(new ResObj<>(ErrCode.UNKNOWN_ERROR));
    }

    public static <T> Mono<ResObj<T>> failure(ErrCode errCode, T monoData) {
        return Mono.just(new ResObj<>(errCode, monoData));
    }
}
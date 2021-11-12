package com.hans.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import reactor.core.publisher.Mono;

@Data
public class ResObj<T> {
    int code;
    String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    T data;

    ResObj(ErrCode errCode) {
        setErrCode(errCode);
    }

    ResObj(ErrCode errCode, T data) {
        setErrCode(errCode);
        this.data = data;
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

    public static <T> Mono<ResObj<T>> success() {
        return Mono.just(new ResObj<>(ErrCode.SUCCESS));
    }

    public static <T> Mono<ResObj<T>> success(Mono<T> monoData) {
        return monoData.map(data -> new ResObj<>(ErrCode.SUCCESS, data));
    }

    // TODO: Consider also accepting Flux?

    public static <T> Mono<ResObj<T>> failure(ErrCode errCode) {
        return Mono.just(new ResObj<>(errCode));
    }

    public static <T> Mono<ResObj<T>> failure(int code, String msg) {
        return Mono.just(new ResObj<>(code, msg));
    }

    public static <T> Mono<ResObj<T>> failure(ErrCode errCode, Mono<T> monoData) {
        return monoData.map(data -> new ResObj<>(errCode, data));
    }
}
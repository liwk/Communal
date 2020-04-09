package com.egee.communallib.retrofit.exception;

/**
 * @Date: 2020/4/6 11:23
 * @Author: LWK
 * @Description:
 * @Version:
 */
public class ResponseException extends Exception {
    private int code;

    private String message;

    public ResponseException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

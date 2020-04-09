package com.egee.communallib.retrofit.exception;

/**
 * @Date: 2020/4/6 11:24
 * @Author: LWK
 * @Description:
 * @Version:
 */
public class ServerException extends RuntimeException {

    public int code;

    public String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.egee.communallib.retrofit.exception;


import com.egee.communallib.retrofit.code.ErrorCode;
import com.egee.communallib.retrofit.code.ServerCode;
import com.egee.communallib.utils.ContextUtils;
import com.egee.communallib.utils.NetworkUtils;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.text.ParseException;


/**
 * @Date: 2019/10/18 11:50
 * @Author: YGZ
 * @Description: 网络请求异常统一处理类
 * @Version:
 */
public class ExceptionHandler {

    /**
     * 处理服务器请求成功，但是业务逻辑失败，比如token失效需要重新登录
     */
    public static ResponseException handleServerException(int code, String message) {
        ServerException serverException = new ServerException();
        serverException.setCode(code);
        serverException.setMessage(message);
        return handleException(serverException);
    }

    /**
     * 处理网络请求异常
     */
    public static ResponseException handleException(Throwable e) {
        ResponseException ex;
        if (NetworkUtils.networknNotConnected(ContextUtils.getContext())) {
            // 网络连接不可用
            ex = new ResponseException(e, ErrorCode.NETWORK_ERROR);
            ex.setMessage("网络不给力哦，请检查一下网络连接吧~");
            return ex;
        } else if (e instanceof ServerException) {
            // 网络连接可用，服务器返回的错误
            ServerException serverException = (ServerException) e;
            int serverCode = serverException.getCode();
            String message = serverException.getMessage();
            ex = new ResponseException(serverException, ErrorCode.SERVER_ERROR);
            ex.setMessage(message);
            // case ServerCode.SERVER_CODE_TOKEN_INVALID:  // Token失效
            if (serverCode == ServerCode.SERVER_CODE_NO_TOKEN) {   // Token为空
                // 跳转到登录页
//                ActivityManagers.startLoginNewTask(ContextUtil.getContext());
            }
            return ex;
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            // 网络连接可用，数据解析错误
            ex = new ResponseException(e, ErrorCode.PARSE_ERROR);
            ex.setMessage("数据解析错误");
            return ex;
        } else if (e instanceof SocketTimeoutException) {
            // 网络连接可用，服务器响应超时
            ex = new ResponseException(e, ErrorCode.TIMEOUT);
            ex.setMessage("服务器响应超时，请稍后再试");
            return ex;
        } else {
            // 网络连接可用，其他错误
            ex = new ResponseException(e, ErrorCode.UNKNOWN);
            ex.setMessage(e.getMessage());
            return ex;
        }
    }

}

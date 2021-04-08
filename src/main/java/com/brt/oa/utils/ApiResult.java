package com.brt.oa.utils;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

public class ApiResult extends HashMap<String,Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    /**
     * 初始化一个新创建的 ApiResult 对象，使其表示一个空消息。
     */
    public ApiResult(){};

    /**
     *
     */

    /**
     * 初始化一个新创建的ApiResule 对象
     * @param code
     * @param msg
     */
    public ApiResult(int code,String msg){
        super.put(CODE_TAG,code);
        super.put(MSG_TAG,msg);
    }

    /**
     *初始化一个新创建的ApiResule 对象
     * @param code
     * @param msg
     * @param data
     */
    public ApiResult(int code,String msg ,Object data){
        super.put(CODE_TAG,code);
        super.put(MSG_TAG,msg);
        if(data != null){
            super.put(DATA_TAG,data);
        }
    }

    /**
     *返回成功消息
     * @return
     */
    public static ApiResult success(){
        return ApiResult.success("操作成功");
    }

    /**
     *返回成功数据
     * @param data
     * @return 成功消息
     */
    public static ApiResult success(Object data) {
        return ApiResult.success("操作成功", data);
    }

    /**
     *返回成功消息
     * @param msg 返回内容
     * @return 成功消息
     */
    public static ApiResult success(String msg) {
        return  ApiResult.success(msg, null);
    }

    /**
     * 返回成功消息
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static ApiResult success(String msg, Object data) {
        return new ApiResult(HttpStatus.OK.value(), msg, data);
    }

    /**
     * 返回错误消息
     * @return
     */
    public static ApiResult error(){
        return ApiResult.error("操作失败");
    }

    /**
     * 返回错误消息
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ApiResult error(String msg){
        return new ApiResult(HttpStatus.INTERNAL_SERVER_ERROR.value(),"操作失败-"+msg);
    }

    /**
     * 返回错误消息
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ApiResult error(String msg , Object data){
        return  new ApiResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, data);
    }

    /**
     * 返回错误消息
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static ApiResult error(int code, String msg) {
        return new ApiResult(code, msg, null);
    }


}

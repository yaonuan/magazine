package com.magazine.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 功能描述：工具类
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018-10-16
 */
@Data
public class JsonData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码 0 表示成功,1 表示处理中,-1 表示失败
     */
    private Integer code;

    /**
     * 数据
     */
    private Object data;

    /**
     * 描述
     */
    private String msg;

    public JsonData() {

    }

    public JsonData(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    // 成功,传入数据
    public static JsonData buildSuccess() {
        return new JsonData(0, null, null);
    }

    // 成功,传入数据
    public static JsonData buildSuccess(Object data) {
        return new JsonData(0, data, null);
    }

    // 失败，传入描述信息
    public static JsonData buildError(String msg) {
        return new JsonData(-1, null, msg);
    }

    // 失败，传入描述信息,状态码
    public static JsonData buildError(Integer code, String msg) {
        return new JsonData(code, null, msg);
    }

    // 成功，传入数据,及描述信息
    public static JsonData buildSuccess(Object data, String msg) {
        return new JsonData(0, data, msg);
    }

    // 成功，传入数据,及状态码
    public static JsonData buildSuccess(Object data, int code) {
        return new JsonData(code, data, null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "JsonData [code=" + code + ", data=" + data + ", msg=" + msg
                + "]";
    }

}

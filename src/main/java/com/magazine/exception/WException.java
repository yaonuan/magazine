package com.magazine.exception;

/**
 * 自定义异常类
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018/12/8 0008
 */
public class WException extends RuntimeException {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 异常消息
     */
    private String msg;

    public WException(int code, String msg){
        super(msg);

        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

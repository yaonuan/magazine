package com.magazine.exception;

import com.magazine.domain.JsonData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理控制器
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018/12/8 0008
 */
@ControllerAdvice
public class WExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData Handler(Exception e){
        if (e instanceof WException){
            WException wException = (WException) e;
            return JsonData.buildError(wException.getCode(),wException.getMsg());
        }else {
            return JsonData.buildError("全局异常");
        }

    }
}

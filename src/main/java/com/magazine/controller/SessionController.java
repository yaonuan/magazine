package com.magazine.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-02-25
 */
@RestController
public class SessionController {

    @GetMapping("/setSession")
    public Map<String,Object> setSession(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        request.getSession().setAttribute("request Url", request.getRequestURI());
        map.put("request Url", request.getRequestURL());
        System.out.println("进入设置seesion模式");
        return map;
    }

    @GetMapping("getSession")
    public Object getSession(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("sessionIdUrl",request.getSession().getAttribute("request Url"));
        map.put("sessionId", request.getSession().getId());
        System.out.println("进入获取seesion模式");
        return map;
    }

}
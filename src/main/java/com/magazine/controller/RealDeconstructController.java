package com.magazine.controller;

import com.magazine.busi.RealComparisonDeconstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/29
 */
@RestController
@RequestMapping("/multi")
public class RealDeconstructController {

    @Autowired
    private RealComparisonDeconstruct comparisonDeconstruct;

    @GetMapping("/dec")
    public void multiDec(){
        boolean comparison = comparisonDeconstruct.comparison();
    }


}

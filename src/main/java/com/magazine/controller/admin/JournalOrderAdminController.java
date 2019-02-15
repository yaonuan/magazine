package com.magazine.controller.admin;

import com.magazine.service.JournalOrderService;
import com.magazine.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户订阅杂志
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-01-10
 */
@RestController
@RequestMapping("/admin/api/v1/journal_order")
public class JournalOrderAdminController {

    @Autowired
    private JournalOrderService service;

    @GetMapping("/add")
    public R saveOrder(@RequestParam(value = "journalId", required = true) Long id, HttpServletRequest request, HttpServletResponse response) {
        service.saveOrder(id, request);
        return R.ok();
    }


}

package com.magazine.controller;

import com.magazine.domain.JournalEntity;
import com.magazine.service.JournalService;
import com.magazine.utils.PageUtils;
import com.magazine.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 杂志控制层
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-01-09
 */
@RestController
@RequestMapping("/api/v1/journal")
public class JournalController {

    @Autowired
    private JournalService service;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 根据id查询杂志信息
     *
     * @param id
     * @return
     */
    @GetMapping("/query_id/{id}")
    public R queryById(@PathVariable Integer id) {
        JournalEntity journalEntity = service.selectById(id);
        return R.ok().put("data", journalEntity);
    }

    /**
     * 获取已上线的杂志分页
     *
     * @param params
     * @return
     */
    @GetMapping("/page")
    public R page(@RequestParam Map<String, Object> params) {
        PageUtils page = service.queryTerm(params);
        return R.ok().put("data", page);
    }


}

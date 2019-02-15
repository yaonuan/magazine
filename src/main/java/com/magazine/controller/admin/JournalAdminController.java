package com.magazine.controller.admin;

import com.magazine.domain.JournalEntity;
import com.magazine.service.JournalService;
import com.magazine.utils.PageUtils;
import com.magazine.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * 管理员-杂志控制层
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018-12-25
 */
@RestController
@RequestMapping(value = "/admin/api/v1/journal")
public class JournalAdminController {

    @Autowired
    private JournalService service;

    Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 管理员分页
     *
     * @param params
     * @return
     */
    @GetMapping("/page")
    public R page(@RequestParam Map<String, Object> params) {
        PageUtils page = service.queryAdminTerm(params);
        return R.ok().put("data", page);
    }

    /**
     * 保存杂志信息
     *
     * @param journalEntity
     * @return
     */
    @PostMapping("/save")
    public R save(@RequestBody JournalEntity journalEntity) {
        service.save(journalEntity);
        return R.ok();
    }

    /**
     * 更新杂志信息
     *
     * @param journalEntity
     * @return
     */
    @PostMapping("/update")
    public R update(@RequestBody JournalEntity journalEntity) {
        service.replace(journalEntity);
        return R.ok();
    }


    /**
     * 审核或取消审核
     *
     * @param id
     * @return
     */
    @GetMapping("set_online")
    public R setOnline(@RequestParam Long id) {
        service.setOnline(id);
        return R.ok();
    }

    /**
     * 单独删除
     *
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return R.ok();
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @PostMapping("/ids")
    public R deleteByIds(@RequestBody Set<Long> ids) {
        service.deleteByIds(ids);
        return R.ok();
    }

}

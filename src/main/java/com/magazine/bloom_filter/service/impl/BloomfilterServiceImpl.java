package com.magazine.bloom_filter.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.magazine.bloom_filter.entity.SysUser;
import com.magazine.bloom_filter.mapper.SysUserMapper;
import com.magazine.bloom_filter.service.BloomfilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/9/11
 */
@Service
public class BloomfilterServiceImpl implements BloomfilterService {


    @Autowired
    private SysUserMapper sysUserMapper;

    private BloomFilter<Integer> bf;

//    @PostConstruct
    public void initBloomFilter() {
        List<SysUser> userList = sysUserMapper.selectList(new EntityWrapper<>());
        if (CollectionUtils.isEmpty(userList)) {
            return;
        }
        bf = BloomFilter.create(Funnels.integerFunnel(), userList.size());
        for (SysUser entity : userList) {
            bf.put(entity.getUserId());
        }
    }

    /**
     * 判断id可能存在布隆过滤器里面
     *
     * @param id
     * @return
     */
    @Override
    public boolean userIdExists(Integer id) {
        return bf.mightContain(id);
    }
}

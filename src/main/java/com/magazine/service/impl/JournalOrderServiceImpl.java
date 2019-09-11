package com.magazine.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.magazine.constant.MagazineConstant;
import com.magazine.domain.JournalEntity;
import com.magazine.domain.JournalOrderEntity;
import com.magazine.mapper.JournalOrderMapper;
import com.magazine.service.JournalOrderService;
import com.magazine.service.JournalService;
import com.magazine.utils.CommonUtils;
import com.magazine.utils.PageUtils;
import com.magazine.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 杂志订阅表
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-01-10
 */
@Service
public class JournalOrderServiceImpl extends ServiceImpl<JournalOrderMapper, JournalOrderEntity> implements JournalOrderService {

    @Autowired
    private JournalService journalService;

    @Override
    public Boolean saveOrder(Long journalId, HttpServletRequest request) {
        JournalOrderEntity entity = new JournalOrderEntity();
//        String ip = IpUtils.getIpAddr(request);
//        Long userId = Long.valueOf(String.valueOf(request.getAttribute("user_id")));
        Long userId = 3L;
        String ip = "39.171.12.6";
        entity.setIp(ip);
        entity.setOutTradeNo(CommonUtils.generateUUID());
        entity.setCreateTime(new Date());
        entity.setJournalId(journalId);
        // 查询当前杂志相关信息
        JournalEntity journalEntity = journalService.selectById(journalId);
        entity.setTotalFee(journalEntity.getPrice());
        entity.setJournalTitle(journalEntity.getTitle());
        entity.setJournalImg(journalEntity.getCoverImg());
        // todo 支付状态后期v1.2开发
        entity.setState(MagazineConstant.FALSE);
        entity.setUserId(userId);
        this.insert(entity);
        System.out.println(entity);
//        return this.insert(entity);
        return true;
    }

    @Override
    public PageUtils<JournalOrderEntity> queryByUserId(Map<String, Object> params, Long userId) {

        Page page = this.selectPage(new Query<JournalOrderEntity>(params).getPage(), new EntityWrapper<JournalOrderEntity>().eq("user_id", userId)
                .like("journal_title", params.get("journalTitle").toString()).eq("is_deleted", MagazineConstant.FALSE));
        return new PageUtils<>(page);
    }

    @Override
    public PageUtils<JournalOrderEntity> queryByOther(Map<String, Object> params) {
        Page page = this.selectPage(new Query<JournalOrderEntity>(params).getPage(), new EntityWrapper<JournalOrderEntity>().eq("is_deleted", MagazineConstant.FALSE)
                .like("journal_title", params.get("journalTitle").toString()).eq("user_id", params.get("userId").toString()).eq("out_trade_no", params.get("outTradeNo")));
        return new PageUtils<>(page);
    }

}

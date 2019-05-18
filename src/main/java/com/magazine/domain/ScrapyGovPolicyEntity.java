package com.magazine.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 政策采集信息
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/16
 */
@Data
public class ScrapyGovPolicyEntity  extends Model<ScrapyGovPolicyEntity> {

    @TableId
    private Long id;

    private String title;

    private String text;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

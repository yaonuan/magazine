package com.magazine.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/20
 */
public class HikaricpDataSource extends UnpooledDataSourceFactory {

    public HikaricpDataSource(){
        this.dataSource = new HikariDataSource();
    }

}

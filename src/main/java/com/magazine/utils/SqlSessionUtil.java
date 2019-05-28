package com.magazine.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * 数据库配置文件
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/20
 */
public class SqlSessionUtil {

    private static SqlSessionFactory ourSqlSessionFactory;
    private static final String OUR = "our";

    private static final String OUR_MIDDLE = "mybatis-config-our.xml";

    private static Reader ourResourceAsReader = null;

    static {
        try {
            ourResourceAsReader = Resources.getResourceAsReader(OUR_MIDDLE);
            ourSqlSessionFactory = new SqlSessionFactoryBuilder().build(ourResourceAsReader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ourResourceAsReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 获取sqlSession
     *
     * @param code
     * @return
     */
    public static SqlSession getSqlSession(String code) {
        if (code.equals(OUR)) {
            return ourSqlSessionFactory.openSession();
        }
        return null;
    }

}

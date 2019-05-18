package com.magazine.thread.pool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义策略
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/17
 */
public class CustomPolicy implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("线程池满了～");
    }
}

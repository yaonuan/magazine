package com.magazine.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池测试Demo
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/16
 */
public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i <= 3; i++) {
            final int task = i;
//            TimeUnit.SECONDS.sleep(1);
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + task);
                }
            });
        }

    }
}

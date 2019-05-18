package com.magazine.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模拟oom
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/17
 */
public class OOMDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        while (true){
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

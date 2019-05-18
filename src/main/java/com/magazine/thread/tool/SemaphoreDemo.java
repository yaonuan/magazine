package com.magazine.thread.tool;

import java.util.concurrent.Semaphore;

/**
 * 信号量
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/15
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(8);

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "开始执行");
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }).start();
        }
    }

}

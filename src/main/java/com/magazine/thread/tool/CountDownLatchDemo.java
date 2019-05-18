package com.magazine.thread.tool;

import java.util.concurrent.CountDownLatch;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/14
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(8);
        new Thread(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("800米比赛结束，准备清空跑道并继续其他比赛～");
        }).start();
        for (int i = 0; i < 8; i++) {
            int finalI = i + 1;
            new Thread(() -> {
                try {
                    Thread.sleep(finalI * 1000L);
                    System.out.println(Thread.currentThread().getName() + "到达终点了～");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }


    }
}

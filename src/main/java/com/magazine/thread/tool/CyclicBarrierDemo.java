package com.magazine.thread.tool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/15
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(8);

        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000L);
                    System.out.println(Thread.currentThread().getName() + "准备就续");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("开始启动比赛");
            }).start();
        }

    }
}

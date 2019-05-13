package com.magazine.thread.hang;

import lombok.Data;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-03-07
 */
public class DeadDemo implements Runnable {

    private static Object object = new Object();

    @Override
    public void run() {

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

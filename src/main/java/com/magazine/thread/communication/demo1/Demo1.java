package com.magazine.thread.communication.demo1;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-04-24
 */
public class Demo1 {

    private static volatile boolean flag = false;

    public static void main(String[] args) {

        Object obj = new Object();

        new Thread(() -> {
            while (!flag) {
                System.out.println("falg is flase");
                synchronized (obj) {

                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("falg is true");
        }).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            flag = true;
            synchronized (obj) {

                obj.notify();
            }
        }).start();
    }

}

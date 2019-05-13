package com.magazine.thread.hang;

/**
 * 理解synchronous
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-03-12
 */
public class SynDemo {

    private Object lock = new Object();

    public static synchronized void staticOut() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(5000L);
    }

    public static void main(String[] args) {
        SynDemo synDemo = new SynDemo();
        SynDemo synDemo1 = new SynDemo();

        new Thread(() -> {
            synDemo.myOut();
        }).start();

        new Thread(() -> {
            synDemo.myOut();
        }).start();

    }

    public synchronized void out() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(5000L);
    }

    public void myOut() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

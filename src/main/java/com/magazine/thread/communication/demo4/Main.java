package com.magazine.thread.communication.demo4;

/**
 * Thread.join通信 线程A在运行时需要调线程B的资源
 * 线程A的run方法里面，调用线程B的join方法，这个时候，线程A会等待线程B运行完成之后，再接着运行
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-04-24
 */
public class Main {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "开始运行!");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "运行结束!");
        }, "线程1");

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "开始运行!");
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "运行结束!");
        }, "线程2").start();
    }

}

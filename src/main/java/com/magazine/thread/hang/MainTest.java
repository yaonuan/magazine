package com.magazine.thread.hang;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-03-14
 */
public class MainTest {

    public static void main(String[] args) {
        Runnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable,"测试线程一");
        thread.start();
        System.out.println("运行结束~");
    }
}

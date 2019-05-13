package com.magazine.thread.lock;

import java.util.concurrent.locks.Lock;

/**
 * 重入demo
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-03-13
 */
public class ReentryDemo {

    public Lock lock = new MyLock();

    public void methodA(){
        lock.lock();
        System.out.println("进入方法A");
        methodB();
        lock.unlock();
    }
    public void methodB(){
        lock.lock();
        System.out.println("进入方法B");
        lock.unlock();
    }

    public static void main(String[] args) {
        ReentryDemo reentryDemo = new ReentryDemo();
        reentryDemo.methodA();
    }

}

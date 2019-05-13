package com.magazine.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁降级Demo
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-04-22
 */
public class LockDegradDemo {

    private int i = 0;

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    Lock readLock = readWriteLock.readLock();
    Lock writeLock = readWriteLock.writeLock();

    public static void main(String[] args) {
        LockDegradDemo lockDegradDemo = new LockDegradDemo();
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                lockDegradDemo.doSomething();
            }).start();

        }
    }

    public void doSomething() {
        writeLock.lock();

        try {
            i++;
            readLock.lock();
        } finally {
            writeLock.unlock();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            if (i == 1) {
                System.out.println("i的数值是=====>1");
            } else {
                System.out.println("i的值是" + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }
}

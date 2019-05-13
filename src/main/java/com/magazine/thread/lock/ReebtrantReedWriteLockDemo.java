package com.magazine.thread.lock;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-03-21
 */
public class ReebtrantReedWriteLockDemo {

    private int i = 0;
    private int j = 0;

    private ReadWriteLock lock = new ReentrantReadWriteLock();
    Lock readLock = lock.readLock();
    Lock writeLock = lock.writeLock();


    public static void main(String[] args) {
        ReebtrantReedWriteLockDemo reebtrantReedWriteLockDemo = new ReebtrantReedWriteLockDemo();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                reebtrantReedWriteLockDemo.inCreate();
                reebtrantReedWriteLockDemo.out();
            }).start();
        }
    }

    public void out() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "i的值===>" + i + "j的值===>" + j);
        } finally {
            readLock.unlock();
        }
    }

    public void inCreate() {
        writeLock.lock();
        try {
            i++;
            try {
                Thread.sleep(500L);
                j++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            writeLock.unlock();
        }
    }

}

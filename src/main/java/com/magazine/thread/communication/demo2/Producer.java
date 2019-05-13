package com.magazine.thread.communication.demo2;

/**
 * 生产者
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-04-24
 */
public class Producer implements Runnable {

    private Medium medium;

    public Producer(Medium medium){
        this.medium = medium;
    }

    @Override
    public void run() {
        while (true){
            medium.put();
        }
    }
}

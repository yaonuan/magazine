package com.magazine.thread.communication.demo2;

/**
 * 消费者
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-04-24
 */
public class Consumer implements Runnable {

    private Medium medium;

    public Consumer(Medium medium){
        this.medium = medium;
    }

    @Override
    public void run() {
        if (true){
            medium.take();
        }
    }
}

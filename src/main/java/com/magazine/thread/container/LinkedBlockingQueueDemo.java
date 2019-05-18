package com.magazine.thread.container;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/14
 */
public class LinkedBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<String> strings = new LinkedBlockingQueue<>();
        // 队列里存元素
        strings.offer("aaa");
        strings.put("bbb");
        // add的方法实际上是使用offer
        strings.add("ccc");

        // 队列里取元素
        String remove = strings.remove();
        strings.put("ccc");
        strings.take();



    }
}

package com.magazine.thread.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/15
 */
public class CallableDemo implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(3000L);
        return "123";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableDemo callableDemo = new CallableDemo();
        FutureTask<String> stringFutureTask = new FutureTask<>(callableDemo);
        new Thread(stringFutureTask).start();

        System.out.println(stringFutureTask.get());

        for (int i = 0; i < 100; i++) {

        }

    }
}

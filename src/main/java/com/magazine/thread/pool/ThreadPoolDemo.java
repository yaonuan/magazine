package com.magazine.thread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程池
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/15
 */
public class ThreadPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        LinkedBlockingQueue<Runnable> objects = new LinkedBlockingQueue<>();

//        for (int i = 0; i < 40; i++) {
//
//            objects.put(()->{
//                System.out.println(Thread.currentThread().getName());
//            });
//        }
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 4, 3L, TimeUnit.SECONDS, objects);

        threadPoolExecutor.prestartAllCoreThreads();

        for (int i = 0; i < 50; i++) {
            final int I = i;
            Future<?> submit = threadPoolExecutor.submit(() -> {
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " " + I);
            });
        }

//        Future<String> submit = null;
//        for (int i = 0; i < 100; i++) {
//            submit = threadPoolExecutor.submit(new CallableDemo());
//        }
//        for (int i = 0; i < 100; i++) {
//            System.out.println(submit.get());
//        }

//        List<List<Integer>> list =  new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            List<Integer> one = new ArrayList<>();
//            for (int j = 0; j < 10; j++) {
//                one.add((i+1)*j);
//            }
//            list.add(one);
//        }
//
//        for (List<Integer> integerList : list) {
//
//            for (Integer integer :integerList) {
//                threadPoolExecutor.execute(new Runnable() {
//                    @Override
//                    public void run() {
//
//                    }
//                });
//            }
//
//        }


    }

}

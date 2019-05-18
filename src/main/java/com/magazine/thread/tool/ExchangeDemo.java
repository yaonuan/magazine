package com.magazine.thread.tool;

import java.util.concurrent.Exchanger;

/**
 * Exchenge交换数据(需要成双成对出现)
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/15
 */
public class ExchangeDemo {
    public static void main(String[] args) {
        Exchanger<String> stringExchanger = new Exchanger<>();

        String str1 = "xiaomo";
        String str2 = "xiaoxuan";
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "初始值=====>"+str1);
            try {
               String exchange = stringExchanger.exchange(str1);
                System.out.println(Thread.currentThread().getName() + "交换后值=====>"+exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"线程1").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "初始值=====>"+str2);
            try {
                String exchange = stringExchanger.exchange(str2);
                System.out.println(Thread.currentThread().getName() + "交换后值=====>"+exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"线程2").start();
    }
}

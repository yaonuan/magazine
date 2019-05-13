package com.magazine.thread.communication.demo1;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-04-24
 */
public class Demo {

    private static volatile boolean flag = false;

    public static void main(String[] args) {
        new Thread(()->{
            while (!flag){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("falg is flase");
            }
            System.out.println("falg is true");
        }).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            flag = true;
        }).start();
    }

}

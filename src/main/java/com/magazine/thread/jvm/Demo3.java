package com.magazine.thread.jvm;


/**
 * 指令重排序
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/18
 */
public class Demo3 {

    static int x = 0, y = 0, a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {

        boolean flag = false;
        int i = 0;
//        while (flag) {

            i++;
            Thread thread = new Thread(() -> {
                a = 1;
                x = b;
            });
            Thread thread1 = new Thread(() -> {
                b = 1;
                y = a;
            });
            System.out.println("第" + i + "x====>" + x + "y====>" + y);
            thread.start();
            thread1.start();
            thread.join();
            thread1.join();
            if (x == 0 && y == 0) {
                flag = false;
            } else {
                x = 0;
                y = 0;
                a = 0;
                b = 0;
            }
//        }
    }

}

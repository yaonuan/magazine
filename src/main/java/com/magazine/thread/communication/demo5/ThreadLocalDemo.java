package com.magazine.thread.communication.demo5;

/**
 * ThreadLocal
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-04-24
 */
public class ThreadLocalDemo {

    ThreadLocal<Integer> num = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();
        for (int i = 0; i < 3; i++) {
            int finalI = i + 1;
            new Thread(() -> {
                while (true) {
                    threadLocalDemo.create();
                    try {
                        Thread.sleep(1000 * finalI);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    /**
     * 自增并输出num的值
     */
    public void create() {
        Integer myNum = num.get();
        myNum++;
        System.out.println(Thread.currentThread().getName() + "------->" + myNum);
        num.set(myNum);
    }

}

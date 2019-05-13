package com.magazine.thread.hang;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-03-14
 */
public class IntUnSafeThread extends Thread {
    private int count =5;

    @Override
    public synchronized void run(){
        super.run();

        count --;
        System.out.println("由" + IntUnSafeThread.currentThread().getName() + " 计算，count=" + count);
    }

    public static void main(String[] args) {
//        IntUnSafeThread intUnSafeThread = new IntUnSafeThread();
//        Thread A = new Thread(intUnSafeThread, "A");
//        Thread B = new Thread(intUnSafeThread, "B");
//        Thread C = new Thread(intUnSafeThread, "C");
//        Thread D = new Thread(intUnSafeThread, "D");
//        Thread E = new Thread(intUnSafeThread, "E");
//
//        A.start();
//        B.start();
//        C.start();
//        D.start();
//        E.start();

        IntUnSafeThread one = new IntUnSafeThread();
        IntUnSafeThread two = new IntUnSafeThread();
        one.start();
        two.start();

    }

}

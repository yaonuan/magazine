package com.magazine.thread.hang;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-03-14
 */
public class InterruptThread extends Thread {

    @Override
    public void run(){
        super.run();
        for (int i = 0;i<500000;i++){
            if (this.isInterrupted()){
                System.out.println("已经是停止状态了!我要退出了!");
                break;
            }
            System.out.println("i=" + (i + 1));
        }
        System.out.println("看到这句话说明线程并未终止------");
    }

    public static void main(String[] args) {

        try {
            InterruptThread thread = new InterruptThread();
            thread.start();
            Thread.sleep(2000L);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

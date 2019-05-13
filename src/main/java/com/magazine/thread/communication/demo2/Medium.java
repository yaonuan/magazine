package com.magazine.thread.communication.demo2;

/**
 * 中间商
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-04-24
 */
public class Medium {

    private static final int TOTAL = 20;
    private int num = 0;

    /**
     * 接收生产数据
     */
    public synchronized void put() {
        // 判断当前库存，是否已经是最大库存的容量
        if (num < TOTAL) {
            // 如果不是，生产完成之后通知消费者进行消费
            System.out.println("新增库存------>当前库存" + ++num);
            notifyAll();
        } else {
            // 如果是，通知生产者进行等待
            try {
                System.out.println("新增库存------>库存已满" + num);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 获取消费数据
     */
    public synchronized void take() {
        //判断当前库存是否不足
        if (num > 0) {
            //如果充足,在消费者完成之后通知生产者进行生产
            System.out.println("消费库存————————>当前库存" + --num);
            notifyAll();
        } else {
            //如果不足,通知消费者暂停

            System.out.println("消费库存不足" + num);
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

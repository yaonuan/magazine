package com.magazine.thread.container;

import java.util.Iterator;
import java.util.Vector;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/14
 */
public class VectorDemo {
    public static void main(String[] args) {
        Vector<String> stringVector = new Vector<>();

        for (int i = 0; i < 1000; i++) {
            stringVector.add("demo" + i);

        }
        // 在foreach中不能进行remove操作
//        stringVector.forEach((e) -> {
//            if (e.equals("demo3")) {
//                stringVector.remove(e);
//            }
//            System.out.println(e);
//        });

        // 正确迭代
        Iterator<String> iterator = stringVector.iterator();

//        while (iterator.hasNext()){
//            String next = iterator.next();
//            if (next.equals("demo3")){
//                iterator.remove();
//            }
//        }
//        System.out.println(stringVector);

        for (int i = 0; i < 4; i++) {
            new Thread(()->{
                synchronized (iterator){
                    while (iterator.hasNext()){
                        String next = iterator.next();
                        if (next.equals("demo3")){
                            iterator.remove();
                        }
                    }
                }
            }).start();
        }

    }
}

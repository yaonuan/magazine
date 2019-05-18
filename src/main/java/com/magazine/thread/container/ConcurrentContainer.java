package com.magazine.thread.container;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 并发容器
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/14
 */
public class ConcurrentContainer {

    public static void main(String[] args) {
        CopyOnWriteArrayList<String> strings = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 1000; i++) {
            strings.add("demo" + i);
        }

//        strings.forEach((e)->{
//            if (e.equals("demo2")){
//                strings.remove(e);
//            }
//        });

//        Iterator<String> iterator = strings.iterator();
//        while (iterator.hasNext()){
//            String next = iterator.next();
//            if (next.equals("demo2")){
//                iterator.remove();
//            }
//        }
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                strings.forEach((e) -> {
                    if (e.equals("demo2")) {
                        strings.remove(e);
                    }
                });
            }).start();
        }


        System.out.println(strings);
    }
}

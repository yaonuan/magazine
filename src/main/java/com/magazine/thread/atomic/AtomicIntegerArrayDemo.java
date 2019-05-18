package com.magazine.thread.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayDemo {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2};
        AtomicIntegerArray array = new AtomicIntegerArray(arr);
        System.out.println(array.addAndGet(1, 2));

        int i = array.accumulateAndGet(0, 2, ((left, right) -> left > right ? left : right));
        System.out.println(i);

        int andGet = array.accumulateAndGet(0, 5, (left, right) -> left * right / 2);


        System.out.println("andGet" + andGet);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.forEach((a) -> System.out.println(a));
    }
}

package com.magazine.thread.container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/14
 */
public class Demo {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        List<String> stringList = Collections.synchronizedList(strings);
    }
}

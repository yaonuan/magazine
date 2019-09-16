package com.magazine.service;

import org.junit.Test;

import java.util.*;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-02-15
 */
public class OtherTest {

    @Test
   public void countDuplicatedList(){

        List list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("b");
        list.add("c");
        list.add("a");
        list.add("a");
        list.add("d");
        list.add("d");
        list.add("d");

        Map<Object,Integer> map = new TreeMap<Object,Integer>();
        Set uniqueSet = new HashSet(list);
        for (Object temp : uniqueSet) {
            System.out.println(temp + ": " + Collections.frequency(list, temp));
            map.put(temp, Collections.frequency(list, temp));
        }
        System.out.println(map);

        // 排序 倒序
        ArrayList<Map.Entry<Object, Integer>> list1 = new ArrayList<>(map.entrySet());

        Collections.sort(list1, new Comparator<Map.Entry<Object, Integer>>() {
            public int compare(Map.Entry<Object, Integer> o1, Map.Entry<Object, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        System.out.println(list1);

    }

    @Test
    public void A(){
        System.out.println(System.currentTimeMillis()/1000);
    }

}

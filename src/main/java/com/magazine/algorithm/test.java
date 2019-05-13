package com.magazine.algorithm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/test")
public class test {

    @GetMapping("/one")
    public void test(int n) {
        double[][] distances = new double[][]{
                {0, 1, 9, 7, 11, 14},
                {1, 0, 4, 3, 8, 10},
                {9, 4, 0, 9, 2, 8},
                {7, 3, 9, 0, 6, 13},
                {11, 8, 2, 6, 0, 10},
                {14, 10, 8, 13, 10, 0}};

        Random random = new Random();

//        int n = 10000;
        double[][] newD = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                newD[i][j] = random.nextDouble();
                newD[j][i] = newD[i][j];
            }
        }

        System.out.println(123);


        HierarchicalCluster hc = new HierarchicalCluster(newD);
        long startTime = System.currentTimeMillis();
        System.out.println("1\t" + hc.getClusters(false, 3.5));
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");

//        long s1 = System.currentTimeMillis();
//        for(int i=0;i<10000;i++){
//            String b = String.valueOf(i);
//        }
//        long s2 = System.currentTimeMillis();
//        System.out.println(s2-s1);


    }


}

package com.magazine.algorithm;

import java.util.ArrayList;

public class Matrics {
    private ArrayList<ArrayList<Double>> matrics;

    public Matrics(double[][] array){
        this.matrics = new ArrayList<>();
        int n = array.length;
        for(int i=0;i<n-1;i++){
            ArrayList<Double> tmp = new ArrayList<>();
            for(int j=i+1;j<n;j++){
                tmp.add(array[i][j]);
            }
            this.matrics.add(tmp);
        }
    }

    //更新矩阵
    public Matrics update(int i,int j,boolean flag){
        for(int index = 0;index<this.matrics.size();index++){
            if(index != i && index != j) {
                this.matrics.get(index).add(this.compare(i, j, index, flag));
            }
            else {
                this.matrics.get(index).add(0.0);
            }
        }
        ArrayList<Double> last = new ArrayList<>();
//        if(i==3 && j==7){
//            System.out.println("helloworld");
//        }
        //TODO 这里在[0,1,3]和[2,4]之间计算出现问题
        last.add(compare(i,j,this.matrics.size(),flag));
        this.matrics.add(last);
        return this;
    }

    //可能有问题，需要二次检查
    public double compare(int i, int j,int target,boolean flag){
        //if flag is true,return complete;else,return single
        //set i < j
        if(target==i || target == j){
            return 0.0;
        }

        double i_target = target<i ? this.matrics.get(target).get(i-target-1) : this.matrics.get(i).get(target-i-1);
        double j_target = target<j ? this.matrics.get(target).get(j-target-1) : this.matrics.get(j).get(target-j-1);
        double max = i_target > j_target ? i_target : j_target;
        double min = i_target < j_target ? i_target : j_target;
        return flag == true ? max : min;
    }

    //在某行中，选择与某个点最近的那个点的坐标
    //还需要考虑多个距离相同的问题
    public Object[] getMaxIndex(int index,ArrayList<String> activeIndexs){
        Object[] returnData = new Object[2];
        double min = 2000000.0;
        int maxIndex=-1;

        long s3 = System.currentTimeMillis();
        for(int i=0;i<index;i++){
            if(activeIndexs.contains(String.valueOf(i))) {
                if (this.matrics.get(i).get(index - i - 1) < min) {
                    min = this.matrics.get(i).get(index - i - 1);
                    maxIndex = i;
                }
            }
        }
        long s4 = System.currentTimeMillis();
        System.out.println("000:\t"+(s4-s3));

        if(index<this.matrics.size()) {
            for (int i = 0; i < this.matrics.get(index).size(); i++) {
                if (this.matrics.get(index).get(i) < min  && activeIndexs.contains(String.valueOf(i + index + 1))) {
                    min = this.matrics.get(index).get(i);
                    maxIndex = i + index + 1;
                }
            }
        }

        returnData[0] = maxIndex;
        returnData[1] = min;
        return returnData;
    }

}

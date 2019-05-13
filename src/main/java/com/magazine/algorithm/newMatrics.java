package com.magazine.algorithm;

import java.util.ArrayList;

public class newMatrics {
    private ArrayList<Double> matrics;
    private int originNum;
    private int num;

    public newMatrics(double[][] array){
        this.matrics = new ArrayList<>();
        int n = array.length;
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                this.matrics.add(array[i][j]);
            }
        }
        this.num = n-1;
        this.originNum = n-1;
    }

    public int size(){
        return this.num;
    }

    public double getValueByCoor(int i,int j){
        int max = i >=j ? i:j;
        int min = i >=j ? j:i;
        int index = -1;
        if(min<=this.originNum && max<=this.originNum){
            index = (int)((-0.5)*min*min + (this.originNum-0.5)*min + max - 1);
        }
        else {

            index = (int)((this.originNum+max+1)*(max-this.originNum)/2-max+min+this.originNum*(this.originNum+1)/2);
        }



        return this.matrics.get(index);
    }


    //更新矩阵
    public newMatrics update(int i, int j, boolean flag){
        for(int index = 0;index<=this.size();index++){
            if(index != i && index != j) {
                this.matrics.add(this.compare(i,j,index,flag));
            }
            else {
                this.matrics.add(0.0);
            }
        }
        this.num = this.num + 1;

        return this;
    }



    //可能有问题，需要二次检查
    public double compare(int i, int j,int target,boolean flag){
        //if flag is true,return complete;else,return single
        //set i < j
        if(target==i || target == j){
            return 0.0;
        }

        double vi = this.getValueByCoor(i,target);
        double vj = this.getValueByCoor(j,target);
        double max = vi >= vj ? vi : vj;
        double min = vi < vj ? vi : vj;

        return flag == true ? max : min;
    }

    //在某行中，选择与某个点最近的那个点的坐标
    //还需要考虑多个距离相同的问题
    public Object[] getMinIndex(int index,ArrayList<String> activeIndexs){

        Object[] returnData = new Object[2];
        double min = 2000000.0;
        int minIndex=-1;

//        long s3 = System.currentTimeMillis();
//        for(int i=0;i<=this.size();i++){
//        long s1 = System.nanoTime();
        for(String i:activeIndexs){
            int t = Integer.parseInt(i);
            //这个判断很费时间
            if(t != index) {
                double tmp = this.getValueByCoor(index,t);
                if ( tmp < min){
                    min = tmp;
                    minIndex = t;
                }
            }

        }
//        long s3 = System.nanoTime();
//        System.out.println(s3-s1);
//        long s4 = System.currentTimeMillis();
//        System.out.println(s4-s3);
        returnData[0] = minIndex;
        returnData[1] = min;
        return returnData;

    }

}

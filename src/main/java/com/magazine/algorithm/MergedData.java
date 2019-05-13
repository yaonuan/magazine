package com.magazine.algorithm;

public class MergedData implements Comparable{
    private Cluster clusterOne;
    private Cluster clusterTwo;
    private double distance;
    private int numBasicCluster;
    private Cluster mergedCluster;

    public Cluster getClusterOne() {
        return clusterOne;
    }

    public void setClusterOne(Cluster clusterOne) {
        this.clusterOne = clusterOne;
    }

    public Cluster getClusterTwo() {
        return clusterTwo;
    }

    public void setClusterTwo(Cluster clusterTwo) {
        this.clusterTwo = clusterTwo;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getNumBasicCluster() {
        return numBasicCluster;
    }

    public void setNumBasicCluster(int numBasicCluster) {
        this.numBasicCluster = numBasicCluster;
    }

    public Cluster getMergedCluster() {
        return mergedCluster;
    }

    public void setMergedCluster(Cluster mergedCluster) {
        this.mergedCluster = mergedCluster;
    }

    public void set(Cluster clusterOne,Cluster clusterTwo,double distance,int numBasicCluster,Cluster mergedCluster){
        this.clusterOne = clusterOne;
        this.clusterTwo = clusterTwo;
        this.distance = distance;
        this.numBasicCluster = numBasicCluster;
        this.mergedCluster = mergedCluster;
    }


    @Override
    public int compareTo(Object o) {
        MergedData mergedData = (MergedData)o;
        if (this.distance > mergedData.distance){
            return 1;
        }
        return -1;
    }
}

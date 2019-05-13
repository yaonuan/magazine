package com.magazine.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cluster {
    private int name;
    private List<Integer> dataPoints;

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public List<Integer> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(List<Integer> dataPoints) {
        this.dataPoints = dataPoints;
    }

    public static List<Cluster> initialCluster(int n){
        List<Cluster> originalClusters = new ArrayList<>();
        for(int i = 0; i < n; i ++){
            List<Integer> tempDataPoints = new ArrayList<Integer>();
            tempDataPoints.add(i);
            Cluster tempCluster = new Cluster();
            tempCluster.setName(i);
            tempCluster.setDataPoints(tempDataPoints);
            originalClusters.add(tempCluster);
        }
        return originalClusters;
    }

    public static Cluster merge(Cluster first,Cluster second,int index){
        ArrayList<Integer> newList = new ArrayList<>();
        for(int i:first.getDataPoints()){
            newList.add(i);
        }
        for(int i:second.getDataPoints()){
            newList.add(i);
        }
        Cluster newCluster = new Cluster();
        newCluster.setDataPoints(newList);
        newCluster.setName(index);
        return newCluster;
    }

    public int size(){
        return this.getDataPoints().size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cluster)) return false;
        Cluster cluster = (Cluster) o;
        return name == cluster.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "clusterName='" + name + "\tdataPoints=" + dataPoints;
    }

}

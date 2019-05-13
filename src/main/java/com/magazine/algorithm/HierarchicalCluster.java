package com.magazine.algorithm;

import java.util.*;

public class HierarchicalCluster {
    private newMatrics matrics;
    private List<Cluster> clusters; //store all clusters
    private List<Cluster> activeClusters; //store active cluster
    private ArrayList<String> activeIndexs; //store active indexs
    private Stack<Cluster> s;
    private ArrayList<MergedData> mergedDataList;
    private List<Cluster> initialClusters;

    public HierarchicalCluster(double[][] m){
        this.matrics = new newMatrics(m);
        this.clusters = Cluster.initialCluster(m.length);
        this.initialClusters = Cluster.initialCluster(m.length);
        this.activeClusters = new LinkedList<>();
        this.activeIndexs = new ArrayList<>();
        for(int i=0;i<this.clusters.size();i++){
            this.activeClusters.add(this.clusters.get(i));
            this.activeIndexs.add(String.valueOf(i));
        }

        this.s = new Stack<>();
        this.mergedDataList = new ArrayList<>();

    }

    public ArrayList<MergedData> update(boolean flag){

        //只要活动的cluster数量大于1
        while (this.activeClusters.size()>1) {

            if(this.activeClusters.size() % 10 ==0){
                System.out.println("111\t"+this.activeClusters.size());
            }

            if (this.s.empty()){
                Random random = new Random();
                //随机获取一个cluster
                int n = random.nextInt(this.activeClusters.size());
//                n=0;
                Cluster baseCluster = this.activeClusters.get(n);
                this.s.push(baseCluster);
            }
            else {
//                long startTime = System.currentTimeMillis();
                Object[] tmp = this.matrics.getMinIndex(this.s.peek().getName(), this.activeIndexs);
//                long endTime = System.currentTimeMillis();
//                System.out.println(endTime-startTime);
                int closestIndex = (int)tmp[0];
                double closestDistance = (double)tmp[1];
                int stackIndex = this.s.search(clusters.get(closestIndex));
                if (stackIndex < 0) {
                    this.s.push(clusters.get(closestIndex));
                }
                else {
                    Cluster first = this.s.pop();
                    Cluster second = this.s.pop();
                    Cluster merged = Cluster.merge(first, second, this.clusters.size());
                    this.activeIndexs.remove(String.valueOf(first.getName()));
                    this.activeIndexs.remove(String.valueOf(second.getName()));
                    this.activeIndexs.add(String.valueOf(this.clusters.size()));
                    this.clusters.add(merged);
                    this.activeClusters.add(merged);
                    this.activeClusters.remove(first);
                    this.activeClusters.remove(second);
                    this.matrics = this.matrics.update(first.getName(),second.getName(),flag);
                    MergedData mergedData = new MergedData();
                    mergedData.set(first,second,closestDistance,merged.size(),merged);
                    this.mergedDataList.add(mergedData);
                }

            }

        }
        return this.mergedDataList;
    }

    public HashSet<Cluster> getClusters(boolean flag,double threshold){
        ArrayList<MergedData> mdl = this.update(flag);
        Collections.sort(mdl);
        HashSet<Cluster> hsLeft = new HashSet<>();
        HashSet<Cluster> hsRight = new HashSet<>();
        hsRight.addAll(this.initialClusters);
        for(MergedData mergedData:mdl){
            if(mergedData.getDistance()<threshold){
                hsLeft.add(mergedData.getClusterOne());
                hsLeft.add(mergedData.getClusterTwo());
                hsRight.add(mergedData.getMergedCluster());
            }
            else {
                break;
            }
        }
        hsRight.removeAll(hsLeft);
        return hsRight;
    }




}
